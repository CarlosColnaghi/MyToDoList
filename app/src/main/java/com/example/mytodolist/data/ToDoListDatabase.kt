package com.example.mytodolist.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [TaskEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class ToDoListDatabase: RoomDatabase() {
    abstract fun TaskDAO(): TaskDAO

    companion object {
        @Volatile
        private var Instance: ToDoListDatabase? = null

        fun getDatabase(context: Context): ToDoListDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, ToDoListDatabase::class.java, "mytodolist")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}