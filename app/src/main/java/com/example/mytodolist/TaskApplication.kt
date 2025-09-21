package com.example.mytodolist

import android.app.Application
import com.example.mytodolist.data.ToDoListDatabase
import com.example.mytodolist.repository.TaskRepository

class TaskApplication: Application() {
    val database by lazy{
        ToDoListDatabase.getDatabase(this)
    }

    val repository by lazy{
        TaskRepository(database.TaskDAO())
    }
}