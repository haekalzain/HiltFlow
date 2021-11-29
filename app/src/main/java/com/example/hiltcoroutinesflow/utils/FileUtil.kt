package com.example.hiltcoroutinesflow.utils

import android.graphics.Bitmap
import android.util.Base64
import java.io.ByteArrayOutputStream

object FileUtil {
    fun Any?.toSafeString(
    ): String {
        return this?.toString() ?: ""
    }
}