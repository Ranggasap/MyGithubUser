package com.example.mygithubuser.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mygithubuser.dataStore.SettingPreferences
import com.example.mygithubuser.viewModel.ThemeViewModel

class ThemeViewModelFactory(private val pref: SettingPreferences): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ThemeViewModel::class.java)){
            return ThemeViewModel(pref) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class"+ modelClass.name)
    }
}