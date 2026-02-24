package com.domain.repository

interface UploadImageRepository {
    suspend fun uploadImageFile(image: ByteArray): Result<String>
}
