package com.example.mygithubuser.data.retrofit

import com.example.mygithubuser.data.response.UserFollowersGithubResponseItem
import retrofit2.Call
import retrofit2.http.*

interface ApiServiceUserFollowers {
    @GET("users/{username}/followers")
    fun getUserFollowers(
        @Path("username") username: String
    ): Call<List<UserFollowersGithubResponseItem>>
}