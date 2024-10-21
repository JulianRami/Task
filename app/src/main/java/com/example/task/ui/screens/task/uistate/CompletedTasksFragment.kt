package com.example.task.ui.screens.task.uistate

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.lifecycle.ViewModelProvider
import com.example.task.R
import com.example.task.data.viewmodel.TaskViewModel
import com.example.task.ui.screens.task.rv.TaskAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CompletedTasksFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CompletedTasksFragment : Fragment() {

    private lateinit var taskViewModel: TaskViewModel
    private lateinit var taskListView: ListView

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_completed_tasks, container, false)
        taskListView = view.findViewById(R.id.completedTaskListView)

        taskViewModel = ViewModelProvider(requireActivity()).get(TaskViewModel::class.java)

        taskViewModel.completedTasks.observe(viewLifecycleOwner, { tasks ->
            val adapter = TaskAdapter(tasks,
                onTaskClick = { /* Manejar la acción de tarea si es necesario */ },
                onTaskCheckChanged = { _, _ -> }
            )
            taskListView.adapter = adapter
        })

        return view
    }
}
