package com.example.mygithubuser.data.response

import com.google.gson.annotations.SerializedName

data class UserFollowersGithubResponse(

	@field:SerializedName("UserFollowersGithubResponse")
	val userFollowersGithubResponse: List<UserFollowersGithubResponseItem>
)

data class UserFollowersGithubResponseItem(

	@field:SerializedName("avatar_url")
	val avatarUrl: String,

	@field:SerializedName("login")
	val login: String
)
