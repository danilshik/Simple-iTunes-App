package ru.ddstudio.simpleitunesapp.data.database.converters

import androidx.room.TypeConverter
import java.util.*

class DateConverter {
    @TypeConverter
    fun fromDate(dateTime: Date): Long {
        return dateTime.time
    }

    @TypeConverter
    fun toDate(dateTime: Long): Date {
        return Date(dateTime)
    }
}