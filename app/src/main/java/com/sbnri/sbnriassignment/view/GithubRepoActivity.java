package com.sbnri.sbnriassignment.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sbnri.sbnriassignment.BaseActivity;
import com.sbnri.sbnriassignment.adapter.GithubRepoAdapter;
import com.sbnri.sbnriassignment.model.GithubRepoModel;
import com.sbnri.sbnriassignment.viewmodel.GithubViewModel;
import com.github.sbnri.R;
import com.github.sbnri.databinding.ActivityMainBinding;

import java.util.List;

public class GithubRepoActivity extends BaseActivity {
  private GithubViewModel githubViewModel;
  private GithubRepoAdapter githubRepoAdapter;
  private boolean loading;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    githubViewModel = ViewModelProviders.of(this).get(GithubViewModel.class);
    setAdapter(activityMainBinding.recylerView);
    getGitIssueList();
  }
  private void setAdapter(RecyclerView recyclerView) {
    final LinearLayoutManager layoutManager=  new LinearLayoutManager(this);
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setHasFixedSize(true);
    recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
    githubRepoAdapter = new GithubRepoAdapter();
    recyclerView.setAdapter(githubRepoAdapter);

    recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
      @Override
      public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        if (dy > 0) {
          int visibleItemCount = layoutManager.getChildCount();
          int totalItemCount = layoutManager.getItemCount();
          int  pastVisiblesItems = layoutManager.findFirstVisibleItemPosition();
          if (loading) {
            if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                   loading = false;
                   githubViewModel.callApi();
            }
          }
        }
      }
    });

  }

  private void getGitIssueList() {
    showDialog();
    githubViewModel.getIssueList().observe(this, new Observer<List<GithubRepoModel>>() {
      @Override
      public void onChanged(@Nullable List<GithubRepoModel> gitIssueModelList) {
        dismissDiaog();
        if(gitIssueModelList!=null&&gitIssueModelList.size()>0){
          githubRepoAdapter.setGitIssueList(gitIssueModelList);
          loading=true;

        }
      }
    });
  }
}
