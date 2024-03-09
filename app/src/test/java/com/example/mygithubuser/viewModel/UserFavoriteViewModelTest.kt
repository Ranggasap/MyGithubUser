package com.example.mygithubuser.viewModel

import android.app.Application
import com.example.mygithubuser.database.UserEntity
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test
import org.mockito.Mockito.mock

class UserFavoriteViewModelTest {

    private val dummyUsername = "sidiqpermana"
    private lateinit var userFavoriteViewModel: UserFavoriteViewModel
    private lateinit var application: Application

    @Before
    fun setup(){
        userFavoriteViewModel = mock(UserFavoriteViewModel::class.java)
    }

    @Test
    fun getAllUsers() {
        userFavoriteViewModel.insert(UserEntity("sidiqpermana", "https://avatars.githubusercontent.com/u/4090245?v=4"))
        val user = userFavoriteViewModel.getAllUsers()
        assertEquals(dummyUsername,user)
    }
}