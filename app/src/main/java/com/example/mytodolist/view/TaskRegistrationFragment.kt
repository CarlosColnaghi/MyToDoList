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
import com.example.mytodolist.R
import com.example.mytodolist.controller.TaskViewModel
import com.example.mytodolist.data.State
import com.example.mytodolist.databinding.FragmentTaskRegistrationBinding
import com.example.mytodolist.domain.Task
import java.time.Instant
import java.util.Calendar
import java.util.Date
import kotlin.text.set

class TaskRegistrationFragment : Fragment() {

    private lateinit var fragmentTaskRegistrationBinding: FragmentTaskRegistrationBinding
    private lateinit var taskStateAdapter: ArrayAdapter<String>

    private val taskViewModel: TaskViewModel by viewModels {
        TaskViewModel.TaskViewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        taskStateAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            State.entries.map{it.displayName}.toTypedArray()
        )
        var deadline: Date? = null
        taskStateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        fragmentTaskRegistrationBinding = FragmentTaskRegistrationBinding.inflate(inflater, container, false).apply {
            stateSpinner.adapter = taskStateAdapter
            stateSpinner.setSelection(0)

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
                taskViewModel.create(
                    Task(
                        name = nameEditText.text.toString(),
                        description = descriptionEditText.text.toString(),
                        deadLine = deadline!!,
                        finishedAt = null,
                        state = State.fromDisplayName(stateSpinner.selectedItem.toString()) ?: State.PENDING,
                        lastState = null
                    )
                )
                findNavController().navigateUp()
            }
        }
        return fragmentTaskRegistrationBinding.root
    }

}