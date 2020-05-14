package com.sbnri.sbnriassignment.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.sbnri.sbnriassignment.model.GithubRepoModel;
import com.sbnri.sbnriassignment.repositary.GithubSbnriRepositary;

import java.util.List;

public class GithubViewModel extends AndroidViewModel {
  private GithubSbnriRepositary githubSbnriRepositary;
  private int page=1;

  public GithubViewModel(@NonNull Application application) {
    super(application);
    githubSbnriRepositary = new GithubSbnriRepositary(application);
  }

  public LiveData<List<GithubRepoModel>> getIssueList() {
    return githubSbnriRepositary.getMutableLiveData(page);
  }
  public void callApi(){
    page=page+1;
    githubSbnriRepositary.getMutableLiveData(page);
  }
}
