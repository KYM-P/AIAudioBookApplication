package com.audiobookmaker.main.ui.main

import androidx.lifecycle.ViewModel
import com.audiobookmaker.main.ui.main.sideeffect.MainSideEffect
import com.audiobookmaker.main.ui.main.state.MainState
import com.domain.model.AudioBookBoard
import com.domain.usecase.UploadImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.persistentListOf
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val uploadImageUseCase: UploadImageUseCase
) : ViewModel(), ContainerHost<MainState, MainSideEffect> {
    override val container = container<MainState, MainSideEffect>(
        initialState = MainState()
    )

    init {
        setTestList()
    }

    private fun setTestList() = intent {
        reduce {
            state.copy(
                bookList = persistentListOf(
                    AudioBookBoard(
                        name = "어린 왕자",
                        createdDate = LocalDate.now(),
                        imageUri = "",
                        pages = 32,
                        id = 1
                    ),
                    AudioBookBoard(
                        name = "백설 공주",
                        createdDate = LocalDate.now(),
                        imageUri = "",
                        pages = 16,
                        id = 2
                    )
                )
            )
        }
    }
}
