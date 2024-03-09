package com.example.mygithubuser.viewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mygithubuser.data.UserRepository
import com.example.mygithubuser.database.UserEntity

class UserFavoriteViewModel(application: Application): ViewModel() {
    private val mUserRepository: UserRepository = UserRepository(application)
    fun insert(userEntity: UserEntity){
        mUserRepository.insert(userEntity)
    }

    fun update(userEntity: UserEntity){
        mUserRepository.update(userEntity)
    }

    fun delete(userEntity: UserEntity){
        mUserRepository.delete(userEntity)
    }

    fun getAllUsers(): LiveData<List<UserEntity>>{
        return mUserRepository.getAllUsers()
    }

    fun getFavoriteUserByUsername(username: String): LiveData<UserEntity>{
        return mUserRepository.getFavoriteUserByUsername(username)
    }
}