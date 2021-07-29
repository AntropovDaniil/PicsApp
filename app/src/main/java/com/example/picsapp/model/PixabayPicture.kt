package com.example.picsapp.model

import com.google.gson.annotations.SerializedName

data class PixabayPicture(

    @SerializedName("id")
    val id: Int,

    @SerializedName("pageURL")
    val pageUrl: String,

    @SerializedName("type")
    val type: String,

    //todo clean model

    @SerializedName("previewURL")
    val previewUrl: String,

    @SerializedName("previewWidth")
    val previewWidth: Int,

    @SerializedName("previewHeight")
    val previewHeight: Int,

    @SerializedName("webformatURL")
    val webformatUrl: String,

    @SerializedName("largeImageURL")
    val largeImageUrl: String,

    @SerializedName("imageWidth")
    val imageWidth: Int,

    @SerializedName("imageHeight")
    val imageHeight: Int,

    @SerializedName("imageSize")
    val imageSize: Int
)
