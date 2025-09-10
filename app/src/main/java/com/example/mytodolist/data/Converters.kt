package com.example.mytodolist.data

import androidx.room.TypeConverter
import java.util.Date

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }

    @TypeConverter
    fun fromState(value: String?): State? {
        return value?.let { State.valueOf(value) }
    }

    @TypeConverter
    fun stateToString(state: State?): String? {
        return state?.toString()
    }
}