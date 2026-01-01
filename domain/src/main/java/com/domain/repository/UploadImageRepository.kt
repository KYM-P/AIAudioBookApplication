package com.domain.repository

import java.io.File

interface UploadImageRepository {
    suspend fun uploadImageFile(image: ByteArray): Result<String>
}