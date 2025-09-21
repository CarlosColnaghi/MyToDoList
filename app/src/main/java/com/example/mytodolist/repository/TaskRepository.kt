package com.example.mytodolist.repository

import com.example.mytodolist.data.TaskDAO
import com.example.mytodolist.domain.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TaskRepository(private val taskDAO: TaskDAO) {
    suspend fun create(task: Task){
        taskDAO.insert(task.toEntity())
    }

    fun getAll(): Flow<List<Task>> = taskDAO.getAll().map { toDoList ->
        toDoList.map{task ->
            task.toDomain()
        }
    }
}