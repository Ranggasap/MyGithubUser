package com.example.mygithubuser.data.response

import com.google.gson.annotations.SerializedName

data class UserFollowingGithubResponse(

	@field:SerializedName("UserFollowingGithubResponse")
	val userFollowingGithubResponse: List<UserFollowingGithubResponseItem>
)

data class UserFollowingGithubResponseItem(

	@field:SerializedName("avatar_url")
	val avatarUrl: String,

	@field:SerializedName("login")
	val login: String
)
