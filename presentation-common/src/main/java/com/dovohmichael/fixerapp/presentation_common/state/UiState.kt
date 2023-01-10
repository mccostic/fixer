package com.dovohmichael.fixerapp.presentation_common.state

sealed class UiState<out T : Any> {
    object Initial : UiState<Nothing>()
    object Loading : UiState<Nothing>()
    data class Error(val errorMessage: String) : UiState<Nothing>()
    data class Success<T : Any>(val data: T) : UiState<T>()
}