package com.example.mytodolist.model.domain

import com.example.mytodolist.model.enums.TaskState
import com.example.mytodolist.model.entity.TaskEntity
import java.util.Date

data class Task(
    val id: Long = 0L,
    val name: String,
    val description: String?,
    val createdAt: Date,
    val updatedAt: Date = Date(),
    val deadLine: Date,
    val finishedAt: Date?,
    var state: TaskState = TaskState.PENDING,
    var lastState: TaskState?
){
    fun toEntity(): TaskEntity{
        return TaskEntity(id, name, description, createdAt, updatedAt, deadLine, finishedAt, state, lastState)
    }
}
