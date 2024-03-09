package com.example.mygithubuser.data.retrofit

import com.example.mygithubuser.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfigUsers {
    companion object{
        fun getApiService(): Retrofit {
            val loggingInterceptor = if(BuildConfig.DEBUG){
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            }else{
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
            }
            val authInterceptor = Interceptor{chain ->
                val req = chain.request()
                val requestHeaders = req.newBuilder()
                    .addHeader("Authorization", BuildConfig.API_KEY)
                    .build()
                chain.proceed(requestHeaders)
            }
            val client = OkHttpClient.Builder()
                .addInterceptor(authInterceptor)
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit
        }
    }
}