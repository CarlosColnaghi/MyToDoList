package com.example.mytodolist.view.adapter

interface ToDoListItemClickListener {
    fun clickOnDoneCheckBox(position: Int, checked: Boolean)

    fun clickOnToDoListItemToEdit(position: Int)

//    fun clickOnMenuItemToDelete(position: Int)
}