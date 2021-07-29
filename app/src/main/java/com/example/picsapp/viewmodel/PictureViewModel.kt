package com.example.picsapp.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.app.WallpaperManager
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import coil.ImageLoader
import coil.request.ImageRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PictureViewModel(application: Application): AndroidViewModel(application) {

    @SuppressLint("StaticFieldLeak")
    private val context = getApplication<Application>().applicationContext

    fun setWallpaper(imageUrl: String){
        val imageLoader = ImageLoader(context)
        val request = ImageRequest.Builder(context)
            .data(imageUrl)
            .build()

        viewModelScope.launch(Dispatchers.IO) {
            val drawable = imageLoader.execute(request).drawable

            withContext(Dispatchers.IO){
                val wallpaperManager = WallpaperManager.getInstance(context)

                //todo add checking
                //if (wallpaperManager.isSetWallpaperAllowed && wallpaperManager.isWallpaperSupported)
                try {
                    val bitmap = (drawable as BitmapDrawable).bitmap
                    wallpaperManager.setBitmap(bitmap)
                } catch (e: Exception){
                    Log.d("TEST_TAG", e.printStackTrace().toString())
                }
            }
        }
    }
}