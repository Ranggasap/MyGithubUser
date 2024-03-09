package com.example.mygithubuser.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mygithubuser.data.response.UsersGithubResponse
import com.example.mygithubuser.data.response.ItemsItem
import com.example.mygithubuser.data.retrofit.ApiConfigUsers
import com.example.mygithubuser.data.retrofit.ApiServiceUsers
import com.example.mygithubuser.utils.Event
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsersViewModel: ViewModel() {
    private val _username = MutableLiveData<String>()
    val username: LiveData<String> = _username

    private val _listUsers = MutableLiveData<List<ItemsItem>>()
    val listUsers: LiveData<List<ItemsItem>> = _listUsers

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _snackbarText = MutableLiveData<Event<String>>()
    val snackbarText: LiveData<Event<String>> = _snackbarText

    companion object{
        private const val TAG = "UsersViewModel"
        private const val DEFAULT_USERNAME = "ranggasap"
    }

    init {
        if(username.value != null){
            findUsers(username.value.toString())
        }else{
            _username.value = DEFAULT_USERNAME
            findUsers(username.value.toString())
        }
    }

    private fun findUsers(username: String){
        _isLoading.value = true
        val client = ApiConfigUsers.getApiService().create(ApiServiceUsers::class.java).getUsers(username)
        client.enqueue(object : Callback<UsersGithubResponse> {
            override fun onResponse(
                call: Call<UsersGithubResponse>,
                response: Response<UsersGithubResponse>
            ) {
                _isLoading.value = false
                if(response.isSuccessful){
                    _listUsers.value = response.body()?.items
                }else{
                    Log.e(TAG, "onFailure: ${response.message()}")
                    _snackbarText.value = Event("Response dari API gagal")
                }
            }

            override fun onFailure(call: Call<UsersGithubResponse>, t: Throwable) {
                _isLoading.value = false
                _snackbarText.value = Event("Data tidak berhasil ditampilkan, coba cek koneksi anda")
            }
        })
    }

    fun searchUsername(searchUsername: String){
        _username.value = searchUsername
        findUsers(username.value.toString())
    }
}