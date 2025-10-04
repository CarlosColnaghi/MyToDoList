package com.example.mytodolist

import android.app.Application
import com.example.mytodolist.model.database.ToDoListDatabase
import com.example.mytodolist.model.repository.TaskRepository

class TaskApplication: Application() {
    val database by lazy{
        ToDoListDatabase.getDatabase(this)
    }

    val repository by lazy{
        TaskRepository(database.TaskDAO())
    }
}