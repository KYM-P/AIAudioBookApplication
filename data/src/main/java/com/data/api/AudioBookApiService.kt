package com.data.api

import com.data.response.UploadImageResponse
import okhttp3.MultipartBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface AudioBookApiService {

    @GET("")
    fun getAudioData()

    @Multipart
    @POST("upload/image")
    suspend fun postImageData(
        @Part image: MultipartBody.Part
    ): UploadImageResponse
}
