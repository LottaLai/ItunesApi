package com.lotta.itunesapi.model

data class FilterModel(
    val key: String,
    val value: String,
    var isClicked: Boolean = false
)