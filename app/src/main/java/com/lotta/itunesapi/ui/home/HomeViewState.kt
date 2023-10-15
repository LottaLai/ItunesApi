package com.lotta.itunesapi.ui.home

data class HomeViewState(
    val isLoading: Boolean = false,
    val successMessage: String = "",
    val errorMessage: String = ""
)