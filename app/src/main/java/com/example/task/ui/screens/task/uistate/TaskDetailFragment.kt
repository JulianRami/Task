package com.example.task.ui.screens.task.uistate

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.task.R
import com.example.task.data.viewmodel.TaskViewModel
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TaskDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class TaskDetailFragment : Fragment() {

    private val taskViewModel: TaskViewModel by viewModels()
    private lateinit var taskDetailTitle: TextView
    private lateinit var taskDetailDescription: TextView
    private lateinit var taskCheckBox: CheckBox

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_task_detail, container, false)

        taskDetailTitle = view.findViewById(R.id.taskDetailTitle)
        taskCheckBox = view.findViewById(R.id.taskCheckBox)

        taskViewModel.selectedTask.observe(viewLifecycleOwner, { task ->
            taskDetailTitle.text = task.name
            taskCheckBox.isChecked = task.isCompleted
        })

        taskCheckBox.setOnCheckedChangeListener { _, isChecked ->
            taskViewModel.updateTaskCompletion(taskViewModel.selectedTask.value!!, isChecked)
        }

        return view
    }
}
