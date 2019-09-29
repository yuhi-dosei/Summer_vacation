package com.example.summer_vacation

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by admin on 2018/03/20.
 */
interface GitHubClient {
    @GET("/users/{username}")
    fun getUser(@Path("username") username: String): Call<User>
}