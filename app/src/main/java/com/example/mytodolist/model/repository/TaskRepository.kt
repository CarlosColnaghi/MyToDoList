package com.example.mytodolist.model.repository

import com.example.mytodolist.model.dao.TaskDAO
import com.example.mytodolist.model.domain.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TaskRepository(private val taskDAO: TaskDAO) {
    suspend fun create(task: Task){
        taskDAO.insert(task.toEntity())
    }

    suspend fun update(task: Task){
        taskDAO.update(task.toEntity())
    }

    fun getAll(): Flow<List<Task>> = taskDAO.getAll().map { toDoList ->
        toDoList.map{task ->
            task.toDomain()
        }
    }

    fun get(id: Long): Flow<Task?> = taskDAO.get(id).map { task ->
        task?.toDomain()
    }

    suspend fun delete(id: Long){
        taskDAO.delete(id)
    }
}