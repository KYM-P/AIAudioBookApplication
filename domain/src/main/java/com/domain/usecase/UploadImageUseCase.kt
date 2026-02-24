package com.domain.usecase

import com.domain.repository.UploadImageRepository
import java.io.File
import javax.inject.Inject

class UploadImageUseCase @Inject constructor(
    private val uploadImageRepository: UploadImageRepository
) {
    suspend operator fun invoke(
        image: ByteArray
    ) = uploadImageRepository.uploadImageFile(image)
}
