package com.audiobookmaker.main.ui.main.state

import com.domain.model.AudioBookBoard
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class MainState (
    val isLoading: Boolean = false,
    val bookList: ImmutableList<AudioBookBoard> = persistentListOf()
)