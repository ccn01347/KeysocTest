package com.example.keysoctest.`class`

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIManager private constructor(){
    private val retrofit: Retrofit
    private val httpClient: OkHttpClient

    init {

        httpClient = OkHttpClient.Builder().build()
        retrofit = Retrofit.Builder()
            .baseUrl("https://itunes.apple.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
    }

    companion object{
        private val manager = APIManager()
        val client: Retrofit
            get() = manager.retrofit
    }


}

