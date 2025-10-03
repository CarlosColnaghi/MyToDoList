package com.example.mytodolist.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDAO {
    @Insert
    suspend fun insert(taskEntity: TaskEntity)

    @Update
    suspend fun update(taskEntity: TaskEntity)

    @Query("SELECT * FROM tasks")
    fun getAll(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM tasks where id = :id")
    fun get(id: Long): Flow<TaskEntity>

    @Query("DELETE FROM tasks WHERE id = :id")
    suspend fun delete(id: Long)
}