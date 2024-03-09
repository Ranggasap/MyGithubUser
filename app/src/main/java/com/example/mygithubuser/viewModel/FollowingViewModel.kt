package com.example.mygithubuser.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mygithubuser.data.response.UserFollowingGithubResponseItem
import com.example.mygithubuser.data.retrofit.ApiConfigUsers
import com.example.mygithubuser.data.retrofit.ApiServiceUserFollowing
import com.example.mygithubuser.utils.Event
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowingViewModel: ViewModel() {
    private val _username = MutableLiveData<String>()
    val username: LiveData<String> = _username

    private val _listUserFollowing = MutableLiveData<List<UserFollowingGithubResponseItem>>()
    val listUserFollowing : LiveData<List<UserFollowingGithubResponseItem>> = _listUserFollowing

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _snackbarText = MutableLiveData<Event<String>>()
    val snackbarText: LiveData<Event<String>> = _snackbarText

    companion object{
        private const val TAG = "FollowingViewModel"
    }

    private fun findUserFollowing(username: String){
        _isLoading.value = true
        val client = ApiConfigUsers.getApiService().create(ApiServiceUserFollowing::class.java).getUserFollowing(username)
        client.enqueue(object : Callback<List<UserFollowingGithubResponseItem>>{
            override fun onResponse(
                call: Call<List<UserFollowingGithubResponseItem>>,
                response: Response<List<UserFollowingGithubResponseItem>>
            ) {
                _isLoading.value = false
                if(response.isSuccessful){
                    _listUserFollowing.value = response.body()
                }else{
                    Log.e(TAG, "onFailure: ${response.message()}")
                    _snackbarText.value = Event("Response dari API gagal")
                }
            }

            override fun onFailure(call: Call<List<UserFollowingGithubResponseItem>>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
                _snackbarText.value = Event("Data tidak berhasil ditampilkan, coba cek koneksi anda")
            }

        })
    }

    fun setUsername(username: String){
        _username.value = username
        findUserFollowing(username)
    }
}