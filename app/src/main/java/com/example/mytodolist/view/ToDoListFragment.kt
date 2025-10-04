package com.example.mytodolist.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mytodolist.R
import com.example.mytodolist.controller.TaskViewModel
import com.example.mytodolist.model.enums.TaskState
import com.example.mytodolist.databinding.FragmentToDoListBinding
import com.example.mytodolist.model.domain.Task
import com.example.mytodolist.view.adapter.TaskAdapter
import com.example.mytodolist.view.adapter.ToDoListItemClickListener
import java.util.Date

class ToDoListFragment : Fragment(), ToDoListItemClickListener {
    private lateinit var fragmentToDoListBinding: FragmentToDoListBinding

    private val taskViewModel: TaskViewModel by viewModels {
        TaskViewModel.TaskViewModelFactory
    }

    private val navController: NavController by lazy{
        findNavController()
    }

    private val toDoList: MutableList<Task> = mutableListOf()

    private val taskAdapter: TaskAdapter by lazy{
        TaskAdapter(toDoList, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        taskViewModel.toDoList.observe(requireActivity()){task ->
            toDoList.clear()
            toDoList.addAll(task)
            taskAdapter.notifyDataSetChanged()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentToDoListBinding = FragmentToDoListBinding.inflate(inflater, container, false).apply {
            toDoListRecyclerView.layoutManager = LinearLayoutManager(context)
            toDoListRecyclerView.adapter = taskAdapter

            taskRegistrationFloatingActionButton.setOnClickListener {
                navController.navigate(
                    R.id.action_toDoListFragment_to_taskRegistrationFragment
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

    override fun clickOnDoneCheckBox(position: Int, checked: Boolean) {
        toDoList[position].apply {
            state = if (checked){
                finishedAt = Date()
                lastState = state
                TaskState.DONE
            } else {
                lastState ?: TaskState.PENDING
            }
            taskViewModel.update(this)
        }
    }

    override fun clickOnToDoListItemToEdit(position: Int) {
        toDoList[position].also {
            navController.navigate(
                R.id.action_toDoListFragment_to_taskRegistrationFragment,
                Bundle().apply {
                    putLong("id", it.id)
                }
            )
        }
    }

    override fun clickOnMenuItemToDelete(position: Int) {
        taskViewModel.delete(toDoList[position].id)
    }

}