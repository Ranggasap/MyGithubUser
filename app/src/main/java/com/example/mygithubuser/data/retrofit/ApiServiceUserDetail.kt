package com.example.mygithubuser.data.retrofit

import com.example.mygithubuser.data.response.UserDetailGithubResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiServiceUserDetail {
    @GET("users/{username}")
    fun getDetailUser(
        @Path("username") username: String
    ): Call<UserDetailGithubResponse>
}