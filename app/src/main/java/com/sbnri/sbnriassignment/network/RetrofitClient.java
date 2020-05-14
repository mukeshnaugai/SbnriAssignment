package com.sbnri.sbnriassignment.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
  private static Retrofit retrofit;
  public static GithubRepoDataService getService() {
    if (retrofit == null) {
      retrofit = new Retrofit
          .Builder()
          .baseUrl("https://api.github.com/orgs/octokit/")
          .addConverterFactory(GsonConverterFactory.create())
          .build();
    }
    return retrofit.create(GithubRepoDataService.class);
  }
}