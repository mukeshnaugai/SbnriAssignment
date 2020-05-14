package com.sbnri.sbnriassignment.network;

import com.sbnri.sbnriassignment.model.GithubRepoModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GithubRepoDataService<T> {
  @GET("repos?page=1&per_page=10")
  Call<ArrayList<GithubRepoModel>> getIssueList();


  @GET("repos?")
  Call<ArrayList<GithubRepoModel>> getIssueListPaging(
          @Query("page") int responseType,
          @Query("per_page") int searchText);

}
