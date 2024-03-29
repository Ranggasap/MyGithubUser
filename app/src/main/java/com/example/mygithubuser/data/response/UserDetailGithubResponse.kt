package com.example.mygithubuser.data.response

import com.google.gson.annotations.SerializedName

data class UserDetailGithubResponse(

	@field:SerializedName("followers")
	val followers: Int,

	@field:SerializedName("avatar_url")
	val avatarUrl: String,

	@field:SerializedName("following")
	val following: Int,

	@field:SerializedName("login")
	val login: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("public_repos")
	val publicRepos: Int,
)
