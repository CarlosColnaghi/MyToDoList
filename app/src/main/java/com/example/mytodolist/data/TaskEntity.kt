package com.example.mytodolist.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mytodolist.domain.Task
import java.time.Instant
import java.util.Date

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val description: String?,
    val createdAt: Date = Date(),
    val updatedAt: Date = Date(),
    val deadLine: Date,
    val finishedAt: Date?,
    val state: State = State.PENDING
){
    fun toDomain(): Task{
        return Task(id, name, description, createdAt, updatedAt, deadLine, finishedAt, state)
    }
}
