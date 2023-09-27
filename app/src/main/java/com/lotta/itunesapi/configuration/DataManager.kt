package com.lotta.itunesapi.configuration

import javax.inject.Inject

class DataManager @Inject constructor(
    private val database: DaoDatabase
    ) {
}