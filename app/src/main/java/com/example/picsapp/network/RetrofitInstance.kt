package com.example.picsapp.network

import com.example.picsapp.consts.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val client = OkHttpClient.Builder().apply {
        addInterceptor(ApiKeyInterceptor())
    }.build()

    private val retrofitInstance by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    val pixabayApi: PixabayApi by lazy {
        retrofitInstance.create(PixabayApi::class.java)
    }
}