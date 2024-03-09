package com.example.mygithubuser.data.retrofit

import com.example.mygithubuser.data.response.UserFollowingGithubResponseItem
import retrofit2.Call
import retrofit2.http.*

interface ApiServiceUserFollowing {
    @GET("users/{username}/following")
    fun getUserFollowing(
        @Path("username") username : String
    ): Call<List<UserFollowingGithubResponseItem>>
}