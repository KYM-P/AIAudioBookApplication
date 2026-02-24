package com.data.repositoryImpl

import com.data.datasource.remote.AudioBookApiDataSource
import com.domain.repository.UploadImageRepository
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.HttpException
import java.io.File
import java.io.IOException
import javax.inject.Inject

class UploadImageRepositoryImpl @Inject constructor(
    private val audioBookApiDataSource: AudioBookApiDataSource
): UploadImageRepository {

    override suspend fun uploadImageFile(
            image: ByteArray
    ): Result<String> {
        val requestBody = image.toRequestBody("image/jpeg".toMediaTypeOrNull(), 0, image.size)
        return runCatching {
            audioBookApiDataSource.uploadImage(
                MultipartBody.Part.createFormData(
                    "image",
                    "captured_image.jpg",
                    requestBody
                )
            ).url
        }.onFailure { e ->
            return Result.failure(
                when (e) {
                    is HttpException -> {
                        when (e.code()) {
                            else -> e //TODO
                        }
                    }
                    is IOException -> {
                        e //TODO
                    }
                    else -> e
                }
            )
        }
    }
}
