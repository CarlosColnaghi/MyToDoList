package com.example.mytodolist.view.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mytodolist.data.State
import com.example.mytodolist.databinding.ToDoListItemBinding
import com.example.mytodolist.domain.Task
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone
import kotlin.or

class TaskAdapter(
    private val toDoList: List<Task>,
    private val toDoListItemClickListener: ToDoListItemClickListener
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder =
        ToDoListItemBinding.inflate(
        LayoutInflater.from(parent.context), parent, false
    ).run{
        TaskViewHolder(this)
    }


    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        toDoList[position].let { task ->
            holder.apply {
                "Name: ${task.name}".also {
                    nameTextView.text = it
                }
                val dateFormat = SimpleDateFormat("dd/MM/yyyy '-' HH:mm", Locale.getDefault())
                dateFormat.timeZone = TimeZone.getDefault()
                "Deadline: ${dateFormat.format(task.deadLine)}".also {
                    deadlineTextView.text = it
                }
                "State: ${task.state.displayName}".also {
                    stateTextView.text = it
                }
                if(task.state == State.DONE){
                    nameTextView.paintFlags = nameTextView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                    deadlineTextView.paintFlags = deadlineTextView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                    stateTextView.paintFlags = stateTextView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                    doneCheckBox.isChecked = true
                }else{
                    nameTextView.paintFlags = nameTextView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                    deadlineTextView.paintFlags = deadlineTextView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                    stateTextView.paintFlags = stateTextView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                    doneCheckBox.isChecked = false
                }
            }
        }
    }

    override fun getItemCount(): Int = toDoList.size

    inner class TaskViewHolder(val todoListItemBinding: ToDoListItemBinding) : RecyclerView.ViewHolder(todoListItemBinding.root){
        val nameTextView: TextView = todoListItemBinding.nameTextView
        val deadlineTextView: TextView = todoListItemBinding.deadlineTextView
        val stateTextView: TextView = todoListItemBinding.stateTextView
        val doneCheckBox: CheckBox = todoListItemBinding.doneCheckBox

        init {
            todoListItemBinding.apply {
                root.run {
                    doneCheckBox.setOnClickListener {
                        toDoListItemClickListener.clickOnDoneCheckBox(bindingAdapterPosition, doneCheckBox.isChecked)
                    }
                }
            }
        }
    }

}