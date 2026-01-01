package com.data.response

import com.google.gson.annotations.SerializedName

data class UploadImageResponse(
    @SerializedName("url")
    val url: String
)
