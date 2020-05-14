package com.sbnri.sbnriassignment.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.sbnri.sbnriassignment.model.GithubRepoModel;
import com.github.sbnri.R;
import com.github.sbnri.databinding.GithubRepoItemListBinding;

import java.util.ArrayList;
import java.util.List;

public class GithubRepoAdapter extends RecyclerView.Adapter<GithubRepoAdapter.GitIssueViewHolder> {
  private List<GithubRepoModel> githubRepoModels=new ArrayList<>();
  @NonNull
  @Override
  public GitIssueViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    GithubRepoItemListBinding gitIssueItemListBinding =
        DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
            R.layout.github_repo_item_list, viewGroup, false);
    return new GitIssueViewHolder(gitIssueItemListBinding);
  }

  @Override
  public void onBindViewHolder(@NonNull GitIssueViewHolder gitIssueViewHolder, int i) {
    GithubRepoModel gitIssueModel = githubRepoModels.get(i);
    gitIssueViewHolder.gitIssueItemListBinding.setGithub(gitIssueModel);
  }

  @Override
  public int getItemCount() {
    if (githubRepoModels != null) {
      return githubRepoModels.size();
    } else {
      return 0;
    }
  }

  public void setGitIssueList(List<GithubRepoModel> issueModelList) {
    if(issueModelList!=null&&issueModelList.size()>0){
      githubRepoModels.addAll(issueModelList);
      notifyDataSetChanged();
    }

  }
  class GitIssueViewHolder extends RecyclerView.ViewHolder {
    private GithubRepoItemListBinding gitIssueItemListBinding;
    public GitIssueViewHolder(@NonNull GithubRepoItemListBinding gitIssueItemListBinding) {
      super(gitIssueItemListBinding.getRoot());
      this.gitIssueItemListBinding = gitIssueItemListBinding;

    }

  }
}
