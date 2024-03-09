package com.example.mygithubuser.data.retrofit

import com.example.mygithubuser.data.response.UsersGithubResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiServiceUsers {
    @GET("search/users")
    fun getUsers(
        @Query("q") username: String
    ): Call<UsersGithubResponse>
}