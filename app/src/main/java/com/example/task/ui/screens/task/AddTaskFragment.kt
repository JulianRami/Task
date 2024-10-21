package com.example.task.ui.screens.task

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.task.R
import com.example.task.data.viewmodel.TaskViewModel
import com.example.task.ui.screens.task.rv.TaskAdapter

class AddTaskFragment : Fragment() {

    private lateinit var taskViewModel: TaskViewModel
    private lateinit var taskListView: ListView

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_task, container, false)
        taskListView = view.findViewById(R.id.taskListView)

        taskViewModel = ViewModelProvider(requireActivity()).get(TaskViewModel::class.java)

        taskViewModel.tasks.observe(viewLifecycleOwner, { tasks ->
            val adapter = TaskAdapter(tasks,
                onTaskClick = { task ->
                    taskViewModel.selectTask(task)
                    findNavController().navigate(R.id.action_addTaskFragment_to_taskDetailFragment)
                },
                onTaskCheckChanged = { task, isChecked ->
                    taskViewModel.updateTaskCompletion(task, isChecked)
                }
            )
            taskListView.adapter = adapter
        })

        val taskInput = view.findViewById<EditText>(R.id.taskInput)
        view.findViewById<Button>(R.id.addTaskButton).setOnClickListener {
            val taskName = taskInput.text.toString()
            if (taskName.isNotEmpty()) {
                taskViewModel.addTask(taskName)
                taskInput.text.clear()
            }
        }

        view.findViewById<Button>(R.id.completedTasksButton).setOnClickListener {
            findNavController().navigate(R.id.completedTasksFragment) // Cambiar aquí
        }

        return view
    }
}
