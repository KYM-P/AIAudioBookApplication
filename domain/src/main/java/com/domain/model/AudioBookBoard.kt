package com.domain.model

import java.time.LocalDate

data class AudioBookBoard(
    val name: String,
    val createdDate: LocalDate,
    val imageUri: String,
    val pages: Int,
    val id: Int
)