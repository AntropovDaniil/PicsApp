package com.example.picsapp.utils

import android.content.Context
import android.content.res.Resources
import android.util.DisplayMetrics

// extension function to convert pixels to equivalent dp
fun Int.pixelsToDp():Float{
    return this / (Resources.getSystem()
        .displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT).toFloat()
}

// input float pixels value to convert equivalent dp
fun Float.pixelsToDp():Float{
    return this / (Resources.getSystem()
        .displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT).toFloat()
}