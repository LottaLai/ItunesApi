package com.lotta.itunesapi.ui.main

sealed class MainViewState {
    object Loading : MainViewState()
    data class Success<T>(val data: T) : MainViewState()
    data class Failed<T>(val error: T) : MainViewState()
}