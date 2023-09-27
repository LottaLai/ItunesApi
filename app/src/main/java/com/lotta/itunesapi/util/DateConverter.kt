package com.lotta.itunesapi.util

import androidx.room.TypeConverter
import java.util.*

class DateConverter {
    companion object{
        @JvmStatic
        @TypeConverter
        fun fromString(value: String?): Date? {
            return value?.let { DateUtil.convertStringToDate(it) }
        }

        @JvmStatic
        @TypeConverter
        fun fromDate(value: Date?): String?{
            return value?.let { DateUtil.convertDateToString(value) }
        }
    }
}