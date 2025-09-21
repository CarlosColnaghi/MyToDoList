package com.example.mytodolist.domain

import com.example.mytodolist.data.State
import com.example.mytodolist.data.TaskEntity
import java.util.Date

data class Task(
    val id: Int,
    val name: String,
    val description: String?,
    val createdAt: Date,
    val updatedAt: Date,
    val deadLine: Date,
    val finishedAt: Date?,
    val state: State
){
    fun toEntity(): TaskEntity{
        return TaskEntity(id, name, description, createdAt, updatedAt, deadLine, finishedAt, state)
    }
}
