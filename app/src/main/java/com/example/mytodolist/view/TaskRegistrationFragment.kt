package com.example.mytodolist.view

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mytodolist.controller.TaskViewModel
import com.example.mytodolist.model.enums.TaskState
import com.example.mytodolist.databinding.FragmentTaskRegistrationBinding
import com.example.mytodolist.model.domain.Task
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class TaskRegistrationFragment : Fragment() {

    private lateinit var fragmentTaskRegistrationBinding: FragmentTaskRegistrationBinding
    private lateinit var taskStateAdapter: ArrayAdapter<String>
    private var taskId: Long? = null

    private var createdAt: Date = Date()

    private val taskViewModel: TaskViewModel by viewModels {
        TaskViewModel.TaskViewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        taskId = arguments?.getLong("id")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        taskStateAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            TaskState.entries.map{it.displayName}.toTypedArray()
        )
        var deadline: Date? = null
        taskStateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        fragmentTaskRegistrationBinding = FragmentTaskRegistrationBinding.inflate(inflater, container, false).apply {
            stateSpinner.adapter = taskStateAdapter
            stateSpinner.setSelection(0)

            if (taskId != null){
                taskViewModel.get(taskId!!).observe(viewLifecycleOwner){ task ->
                    task?.let {
                        nameEditText.setText(task.name)
                        descriptionEditText.setText(task.description)
                        stateSpinner.setSelection(taskStateAdapter.getPosition(task.state.displayName))
                        //TODO: think in a better pattern for date format
                        val dateFormat = SimpleDateFormat("dd/MM/yyyy '-' HH:mm", Locale.getDefault())
                        dateFormat.timeZone = TimeZone.getDefault()
                        deadline = task.deadLine
                        deadlineEditText.setText(dateFormat.format(task.deadLine))
                        createdAt = task.createdAt
                        "Created at: ${dateFormat.format(task.createdAt)}".also {
                            createdAtTextView.text = it
                            createdAtTextView.visibility = View.VISIBLE
                        }
                        "Updated at: ${dateFormat.format(task.updatedAt)}".also {
                            updatedAtTextView.text = it
                            updatedAtTextView.visibility = View.VISIBLE
                        }
                        if(task.finishedAt != null){
                            "Finished at: ${dateFormat.format(task.finishedAt)}".also {
                                finishedAtTextView.text = it
                                finishedAtTextView.visibility = View.VISIBLE
                            }
                        }
                    }
                }
                deleteButton.visibility = View.VISIBLE
            }

            deadlineEditText.setOnClickListener {
                val calendar = Calendar.getInstance()

                val datePicker = DatePickerDialog(
                    requireContext(),
                    { _, year, month, dayOfMonth ->
                        val timePicker = TimePickerDialog(
                            requireContext(),
                            { _, hourOfDay, minute ->
                                deadline = Calendar.getInstance().apply {
                                    set(year, month, dayOfMonth, hourOfDay, minute, 0)
                                }.time
                                deadlineEditText.setText(
                                    "%02d/%02d/%04d - %02d:%02d".format(dayOfMonth, month + 1, year, hourOfDay, minute)
                                )
                            },
                            calendar.get(Calendar.HOUR_OF_DAY),
                            calendar.get(Calendar.MINUTE),
                            true
                        )
                        timePicker.show()
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                )
                datePicker.show()
            }

            saveButton.setOnClickListener {
                var taskState = TaskState.fromDisplayName(stateSpinner.selectedItem.toString()) ?: TaskState.PENDING
                if(taskId != null){
                    taskViewModel.update(
                        Task(
                            taskId!!,
                            name = nameEditText.text.toString(),
                            description = descriptionEditText.text.toString(),
                            createdAt,
                            deadLine = deadline!!,
                            finishedAt = if(taskState == TaskState.DONE) Date() else null,
                            state =taskState,
                            lastState = null
                        )
                    )
                } else {
                    taskViewModel.create(
                        Task(
                            name = nameEditText.text.toString(),
                            description = descriptionEditText.text.toString(),
                            createdAt = Date(),
                            deadLine = deadline!!,
                            finishedAt = if(taskState == TaskState.DONE) Date() else null,
                            state = taskState,
                            lastState = null
                        )
                    )
                }

                findNavController().navigateUp()
            }
            deleteButton.setOnClickListener {
                if(taskId != null){
                    taskViewModel.delete(taskId!!)
                }
                findNavController().navigateUp()
            }
        }
        return fragmentTaskRegistrationBinding.root
    }

}