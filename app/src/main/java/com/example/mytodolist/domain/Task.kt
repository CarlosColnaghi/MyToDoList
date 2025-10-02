package com.example.mytodolist.domain

import com.example.mytodolist.data.State
import com.example.mytodolist.data.TaskEntity
import java.util.Date

data class Task(
    val id: Long = 0L,
    val name: String,
    val description: String?,
    val createdAt: Date = Date(),
    val updatedAt: Date = Date(),
    val deadLine: Date,
    val finishedAt: Date?,
    var state: State = State.PENDING,
    var lastState: State?
){
    fun toEntity(): TaskEntity{
        return TaskEntity(id, name, description, createdAt, updatedAt, deadLine, finishedAt, state, lastState)
    }
}
