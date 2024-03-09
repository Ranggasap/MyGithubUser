package com.example.mygithubuser.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mygithubuser.data.response.UserFollowersGithubResponseItem
import com.example.mygithubuser.data.retrofit.ApiConfigUsers
import com.example.mygithubuser.data.retrofit.ApiServiceUserFollowers
import com.example.mygithubuser.utils.Event
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowerViewModel: ViewModel() {
    private val _username = MutableLiveData<String>()
    val username: LiveData<String> = _username

    private val _listUserFollowers = MutableLiveData<List<UserFollowersGithubResponseItem>>()
    val listUserFollowers: LiveData<List<UserFollowersGithubResponseItem>> = _listUserFollowers

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _snackbarText = MutableLiveData<Event<String>>()
    val snackbarText: LiveData<Event<String>> = _snackbarText

    companion object{
        private const val TAG = "FollowerViewModel"
    }

    private fun findUserFollowers(username: String){
        _isLoading.value = true
        val client = ApiConfigUsers.getApiService().create(ApiServiceUserFollowers::class.java).getUserFollowers(username)
//        val client = ApiConfigUserFollowers.getApiServiceUserFollowers().getUserFollowers(username)
        client.enqueue(object : Callback<List<UserFollowersGithubResponseItem>>{
            override fun onResponse(
                call: Call<List<UserFollowersGithubResponseItem>>,
                response: Response<List<UserFollowersGithubResponseItem>>
            ) {
                _isLoading.value = false
                if(response.isSuccessful){
                    _listUserFollowers.value = response.body()
                }else{
                    Log.e(TAG, "onFailure: ${response.message()}")
                    _snackbarText.value = Event("Response dari API gagal")
                }
            }

            override fun onFailure(call: Call<List<UserFollowersGithubResponseItem>>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
                _snackbarText.value = Event("Data tidak berhasil ditampilkan, coba cek koneksi anda")
            }
        })
    }

    fun setUsername(username: String){
        _username.value = username
        findUserFollowers(username)
    }
}