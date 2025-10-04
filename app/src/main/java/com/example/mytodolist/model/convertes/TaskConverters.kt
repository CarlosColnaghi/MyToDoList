package com.example.mytodolist.model.convertes

import androidx.room.TypeConverter
import com.example.mytodolist.model.enums.TaskState
import java.util.Date

class TaskConverters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }

    @TypeConverter
    fun fromState(value: String?): TaskState? {
        return value?.let { TaskState.valueOf(value) }
    }

    @TypeConverter
    fun stateToString(state: TaskState?): String? {
        return state?.toString()
    }
}