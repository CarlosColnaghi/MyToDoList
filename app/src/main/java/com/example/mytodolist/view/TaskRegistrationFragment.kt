package com.example.mytodolist.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.mytodolist.R
import com.example.mytodolist.data.State
import com.example.mytodolist.databinding.FragmentTaskRegistrationBinding

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
        }
        return fragmentTaskRegistrationBinding.root
    }

}