package com.audiobookmaker.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.domain.usecase.UploadImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val uploadImageUseCase: UploadImageUseCase
) : ViewModel() {

    fun uploadImageFile(image: ByteArray) {
        viewModelScope.launch {
            Log.e("MYLOG","upload")
            uploadImageUseCase.invoke(image).onFailure { e ->
                Log.e("MYLOG", "${e}")
            }
        }
    }
}