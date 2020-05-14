package com.sbnri.sbnriassignment.repositary;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;


import com.sbnri.sbnriassignment.model.GithubRepoModel;
import com.sbnri.sbnriassignment.network.GithubRepoDataService;
import com.sbnri.sbnriassignment.network.RetrofitClient;
import com.sbnri.sbnriassignment.utils.GitIhubEnum;
import com.sbnri.sbnriassignment.utils.InternetConnectivityManger;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GithubSbnriRepositary {
  private ArrayList<GithubRepoModel> githubModelArrayList = new ArrayList<>();
  private MutableLiveData<List<GithubRepoModel>> mutableLiveData = new MutableLiveData<>();
  private Realm mRealm;
  private Context context;
  public GithubSbnriRepositary(Context context)
  {
      this.context=context;
      mRealm = Realm.getDefaultInstance();

  }

  public MutableLiveData<List<GithubRepoModel>> getMutableLiveData(int page) {
      if(InternetConnectivityManger.isConnectingToInternet(context)){
          GithubRepoDataService gitIssueDataService = RetrofitClient.getService();
          Call<ArrayList<GithubRepoModel>> call = gitIssueDataService.getIssueListPaging(page, GitIhubEnum.KeyName.PAGE_NUMBER);
          call.enqueue(new Callback<ArrayList<GithubRepoModel>>() {
              @Override
              public void onResponse(Call<ArrayList<GithubRepoModel>> mainModel, Response<ArrayList<GithubRepoModel>> response) {
                  if (mainModel != null) {
                      githubModelArrayList = response.body();
                      mutableLiveData.setValue(githubModelArrayList);
                      storeDataInRealm(githubModelArrayList);
                  }
              }
              @Override
              public void onFailure(Call<ArrayList<GithubRepoModel>> call, Throwable t) {

              }

          });
      }
      else{
          RealmResults<GithubRepoModel> offlineIssues = mRealm.where(GithubRepoModel.class).findAll();
          githubModelArrayList.addAll(offlineIssues);
          mutableLiveData.setValue(githubModelArrayList);

      }

      return mutableLiveData;
  }

  private void storeDataInRealm(ArrayList<GithubRepoModel>models) {
      mRealm.beginTransaction();
      RealmList<GithubRepoModel> books=new RealmList<>();
      books.addAll(models);
      mRealm.insertOrUpdate(books);
       mRealm.commitTransaction();

  }

}
