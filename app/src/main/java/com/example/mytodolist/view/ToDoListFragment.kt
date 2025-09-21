package com.example.mytodolist.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.mytodolist.R
import com.example.mytodolist.controller.TaskViewModel
import com.example.mytodolist.databinding.FragmentToDoListBinding

class ToDoListFragment : Fragment() {
    private lateinit var fragmentToDoListBinding: FragmentToDoListBinding

    private val taskViewModel: TaskViewModel by viewModels {
        TaskViewModel.TaskViewModelFactory
    }

    private val navController: NavController by lazy{
        findNavController()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentToDoListBinding = FragmentToDoListBinding.inflate(inflater, container, false).apply {
            taskRegistrationFloatingActionButton.setOnClickListener {
                navController.navigate(
                    R.id.action_taskListFragment_to_taskRegistrationFragment
                )
            }
        }
        return fragmentToDoListBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        taskViewModel.toDoList.observe(viewLifecycleOwner) { toDoList ->
            if (toDoList.isEmpty()) {
                fragmentToDoListBinding.emptyList.visibility = View.VISIBLE
            } else {
                fragmentToDoListBinding.emptyList.visibility = View.GONE
            }
        }
    }
}