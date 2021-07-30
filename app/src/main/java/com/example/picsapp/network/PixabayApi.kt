package com.example.picsapp.network

import androidx.annotation.IntRange
import com.example.picsapp.consts.Constants.Companion.API_KEY
import com.example.picsapp.model.PixabayResponse
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApi {

    companion object {

        const val DEFAULT_PAGE_SIZE = 20
        const val MAX_PAGE_SIZE = 20
    }

    @GET("api/")
    suspend fun getPicturesFromCategory(
        @Query("category") categoryName: String,
        @Query("page") @IntRange(from = 1) page: Int = 1,
        @Query("per_page") @IntRange(from = 1, to = MAX_PAGE_SIZE.toLong()) pageSize: Int = DEFAULT_PAGE_SIZE)
    : Response<PixabayResponse>




}

