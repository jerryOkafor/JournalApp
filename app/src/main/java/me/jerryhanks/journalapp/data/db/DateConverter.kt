package me.jerryhanks.journalapp.data.db

import android.arch.persistence.room.TypeConverter

import java.util.Date

/**
 * A type converter for java.util.Date
 * */
class DateConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return if (value == null) null else Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}
 