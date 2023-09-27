package com.lotta.itunesapi.util

import java.text.SimpleDateFormat
import java.util.*

class DateUtil {
    companion object {
        fun convertStringToDate(
            string: String
        ): Date {
            val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
            return format.parse(string) as Date
        }

        fun convertDateToString(
            date: Date
        ): String {
            val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
            return format.format(date)
        }
    }
}