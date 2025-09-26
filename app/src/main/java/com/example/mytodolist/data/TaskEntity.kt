package com.example.mytodolist.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mytodolist.domain.Task
import java.time.Instant
import java.util.Date

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val description: String?,
    val createdAt: Date,
    val updatedAt: Date,
    val deadLine: Date,
    val finishedAt: Date?,
    val state: State,
    val lastState: State?
){
    fun toDomain(): Task{
        return Task(id, name, description, createdAt, updatedAt, deadLine, finishedAt, state, lastState)
    }
}
