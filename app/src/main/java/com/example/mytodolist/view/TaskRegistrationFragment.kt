package com.example.mytodolist.view

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.mytodolist.R
import com.example.mytodolist.data.State
import com.example.mytodolist.databinding.FragmentTaskRegistrationBinding
import java.util.Calendar

class TaskRegistrationFragment : Fragment() {

    private lateinit var fragmentTaskRegistrationBinding: FragmentTaskRegistrationBinding
    private  lateinit var taskStateAdapter: ArrayAdapter<String>

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
                                deadlineEditText.setText(
                                    "%02d/%02d/%04d at %02d:%02d".format(dayOfMonth, month + 1, year, hourOfDay, minute)
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
        }
        return fragmentTaskRegistrationBinding.root
    }

}