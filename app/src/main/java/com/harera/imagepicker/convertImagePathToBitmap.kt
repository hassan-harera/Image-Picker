package com.harera.imagepicker

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import java.net.URI

fun convertImagePathToBitmap(uri: Uri?): Bitmap? =
    BitmapFactory.decodeFile(uri?.path)
