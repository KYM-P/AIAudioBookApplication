package com.data.datasource.remote

import com.data.api.AudioBookApiService
import okhttp3.MultipartBody
import javax.inject.Inject

class AudioBookApiDataSource @Inject constructor(
    private val audioBookApiService: AudioBookApiService
) {
    suspend fun uploadImage(
        image: MultipartBody.Part
    ) = audioBookApiService.postImageData(image)
}
