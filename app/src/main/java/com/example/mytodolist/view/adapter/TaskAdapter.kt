package com.example.mytodolist.view.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.mytodolist.R
import com.example.mytodolist.model.enums.TaskState
import com.example.mytodolist.databinding.ToDoListItemBinding
import com.example.mytodolist.model.domain.Task
import com.example.mytodolist.util.DateFormatUtil
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

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
                "Deadline: ${DateFormatUtil.getStandardDateFormat(task.deadLine)}".also {
                    deadlineTextView.text = it
                }
                "State: ${task.state.displayName}".also {
                    stateTextView.text = it
                }
                if(task.state == TaskState.DONE){
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
                    setOnClickListener {
                        toDoListItemClickListener.clickOnToDoListItemToEdit(bindingAdapterPosition)
                    }
                    doneCheckBox.setOnClickListener {
                        toDoListItemClickListener.clickOnDoneCheckBox(bindingAdapterPosition, doneCheckBox.isChecked)
                    }
                    setOnCreateContextMenuListener { menu, _, _ ->
                        (toDoListItemClickListener as? Fragment)?.activity?.menuInflater?.inflate(
                            R.menu.menu,
                            menu
                        )
                        menu?.findItem(R.id.deleteMenuItem)?.setOnMenuItemClickListener {
                            toDoListItemClickListener.clickOnMenuItemToDelete(bindingAdapterPosition)
                            true
                        }
                    }
                }
            }
        }
    }

}