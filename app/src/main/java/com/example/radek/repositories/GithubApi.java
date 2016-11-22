package com.example.radek.repositories;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubApi {

    @GET("users/{user}/repos")
    Call<List<GithubRepository>> listRepositories(@Path("user") String user);
}
