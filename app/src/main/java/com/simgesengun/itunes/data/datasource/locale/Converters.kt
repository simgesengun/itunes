package com.simgesengun.itunes.data.datasource.locale

import androidx.room.TypeConverter
import java.util.Date

class Converters {
    @TypeConverter
    fun timestampToDate(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}