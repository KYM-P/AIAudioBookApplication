package com.audiobookmaker.util

import android.graphics.ImageFormat
import androidx.camera.core.ImageProxy

fun imageProxyToJpegByteArray(imageProxy: ImageProxy): ByteArray? {
    if (imageProxy.format != ImageFormat.JPEG) {

        return null
    }

    try {
        val buffer = imageProxy.planes[0].buffer
        val bytes = ByteArray(buffer.remaining())

        buffer.get(bytes)

        return bytes
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return null
}