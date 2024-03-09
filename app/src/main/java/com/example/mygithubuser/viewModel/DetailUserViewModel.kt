package com.example.mygithubuser.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mygithubuser.data.response.UserDetailGithubResponse
import com.example.mygithubuser.data.retrofit.ApiConfigUsers
import com.example.mygithubuser.data.retrofit.ApiServiceUserDetail
import com.example.mygithubuser.utils.Event
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel: ViewModel() {
    private val _username = MutableLiveData<String>()
    val username: LiveData<String> = _username

    private val _detailUser = MutableLiveData<UserDetailGithubResponse>()
    val detailUser: LiveData<UserDetailGithubResponse> = _detailUser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _snackbarText = MutableLiveData<Event<String>>()
    val snackbarText: LiveData<Event<String>> = _snackbarText

    companion object{
        private const val TAG = "DetailUserViewModel"
    }

    private fun findDetailUser(username: String){
        _isLoading.value = true
        val client = ApiConfigUsers.getApiService().create(ApiServiceUserDetail::class.java).getDetailUser(username)
        client.enqueue(object: Callback<UserDetailGithubResponse>{
            override fun onResponse(
                call: Call<UserDetailGithubResponse>,
                response: Response<UserDetailGithubResponse>
            ) {
                _isLoading.value = false
                if(response.isSuccessful){
                    _detailUser.value = response.body()
                }else{
                    Log.e(TAG, "onFailure: ${response.message()}")
                    _snackbarText.value = Event("Response dari API gagal")
                }
            }

            override fun onFailure(call: Call<UserDetailGithubResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
                _snackbarText.value = Event("Data tidak berhasil ditampilkan, coba cek koneksi anda")
            }

        })
    }

    fun setUsername(dataUsername: String){
        _username.value = dataUsername
        findDetailUser(username.value.toString())
    }
}