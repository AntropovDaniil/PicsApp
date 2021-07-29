package com.example.picsapp.repository

import com.example.picsapp.model.PixabayResponse
import com.example.picsapp.network.RetrofitInstance
import retrofit2.Response

class CategoryRepository {

    suspend fun getPictureFromCategory(categoryName: String): Response<PixabayResponse> {
        return RetrofitInstance.pixabayApi.getPicturesFromCategory(categoryName)
    }
}