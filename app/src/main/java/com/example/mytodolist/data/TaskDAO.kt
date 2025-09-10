package com.example.mytodolist.data

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface TaskDAO {
    @Insert
    suspend fun insert(taskEntity: TaskEntity)
}