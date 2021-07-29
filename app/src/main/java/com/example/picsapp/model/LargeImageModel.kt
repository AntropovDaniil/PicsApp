package com.example.picsapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LargeImageModel(
    val largeImageUrl: String,
    val imageWidth: Int,
    val imageHeight: Int
): Parcelable
