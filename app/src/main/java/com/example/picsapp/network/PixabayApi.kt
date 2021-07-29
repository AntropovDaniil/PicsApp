package com.example.picsapp.network

import com.example.picsapp.consts.Constants.Companion.API_KEY
import com.example.picsapp.model.PixabayResponse
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApi {

    @GET("api/")
    suspend fun getPicturesFromCategory(
        @Query("category") categoryName: String)
    : Response<PixabayResponse>

}