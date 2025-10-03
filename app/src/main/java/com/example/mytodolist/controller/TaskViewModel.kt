package com.example.mytodolist.controller

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.mytodolist.TaskApplication
import com.example.mytodolist.domain.Task
import com.example.mytodolist.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(private val repository: TaskRepository): ViewModel() {

    fun create(task: Task) = viewModelScope.launch(Dispatchers.IO) {
        repository.create(task)
    }

    fun update(task: Task) = viewModelScope.launch(Dispatchers.IO){
        repository.update(task)
    }

    val toDoList = repository.getAll().asLiveData()

    fun get(id: Long) = repository.get(id).asLiveData()

    fun delete(id: Long) = viewModelScope.launch(Dispatchers.IO){
        repository.delete(id)
    }

    companion object{
        val TaskViewModelFactory = object: ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T =
                TaskViewModel(
                    (checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]) as TaskApplication).repository
                ) as T
        }
    }
}