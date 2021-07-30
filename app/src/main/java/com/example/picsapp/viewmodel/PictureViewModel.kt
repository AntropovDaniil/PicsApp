package com.example.picsapp.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.app.WallpaperManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.util.DisplayMetrics
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import coil.ImageLoader
import coil.request.ImageRequest
import com.example.picsapp.model.LargeImageModel
import com.example.picsapp.model.PixabayPicture
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PictureViewModel(application: Application): AndroidViewModel(application) {

    @SuppressLint("StaticFieldLeak")
    private val context = getApplication<Application>().applicationContext

    fun setWallpaper(image: LargeImageModel){
        val imageLoader = ImageLoader(context)
        val request = ImageRequest.Builder(context)
            .data(image.largeImageUrl)
            .build()

        viewModelScope.launch(Dispatchers.IO) {
            val drawable = imageLoader.execute(request).drawable

            withContext(Dispatchers.IO){
                val wallpaperManager = WallpaperManager.getInstance(context)

                val bitmap = (drawable as BitmapDrawable).bitmap

                val displayMetrics = context.resources.displayMetrics
                val screenWidth = displayMetrics.widthPixels
                val screenHeight = displayMetrics.heightPixels
                wallpaperManager.suggestDesiredDimensions(screenWidth, screenHeight)

                val imageWidth = bitmap.width //wallpaperManager.desiredMinimumWidth
                val imageHeight = bitmap.height //wallpaperManager.desiredMinimumHeight

                val horizontalScaleFactor: Float = imageWidth.toFloat()/screenWidth.toFloat()
                val verticalScaleFactor: Float = imageHeight.toFloat()/screenHeight.toFloat()

                val scaleFactor = Math.max(verticalScaleFactor, horizontalScaleFactor)

                val finalWidth = (imageWidth/scaleFactor).toInt()
                val finalHeight = (imageHeight/scaleFactor).toInt()

                val screenBitmap = Bitmap.createScaledBitmap(bitmap, finalWidth, finalHeight, true)

                try {
                    wallpaperManager.setBitmap(screenBitmap)
                } catch (e: Exception){
                    Log.d("TEST_TAG", e.printStackTrace().toString())
                }
            }
        }
    }
}