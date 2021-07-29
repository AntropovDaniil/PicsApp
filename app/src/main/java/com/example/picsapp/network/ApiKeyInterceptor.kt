package com.example.picsapp.network

import com.example.picsapp.consts.Constants.Companion.API_KEY
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .url()
            .newBuilder()
            .addQueryParameter("key", API_KEY)
            .build()
        val original = chain.request().newBuilder().url(request).build()
        return chain.proceed(original)
    }
}