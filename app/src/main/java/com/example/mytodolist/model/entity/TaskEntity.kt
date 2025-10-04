package com.example.mytodolist.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mytodolist.model.domain.Task
import com.example.mytodolist.model.enums.TaskState
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
    val state: TaskState,
    val lastState: TaskState?
){
    fun toDomain(): Task {
        return Task(
            id,
            name,
            description,
            createdAt,
            updatedAt,
            deadLine,
            finishedAt,
            state,
            lastState
        )
    }
}