package com.example.mygithubuser.viewModelFactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mygithubuser.viewModel.UserFavoriteViewModel

class UserViewModelFactory private constructor(private val mApplication: Application): ViewModelProvider.NewInstanceFactory(){
    companion object{
        @Volatile
        private var INSTANCE: UserViewModelFactory? = null

        @JvmStatic
        fun getInstance(application: Application): UserViewModelFactory{
            if(INSTANCE == null){
                synchronized(UserViewModelFactory::class.java){
                    INSTANCE = UserViewModelFactory(application)
                }
            }
            return INSTANCE as UserViewModelFactory
        }

    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(UserFavoriteViewModel::class.java)){
            return UserFavoriteViewModel(mApplication) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}