package com.example.task.ui.screens.task.rv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.TextView
import com.example.task.R
import com.example.task.data.model.Task

class TaskAdapter(
    private val tasks: List<Task>,
    private val onTaskClick: (Task) -> Unit,
    private val onTaskCheckChanged: (Task, Boolean) -> Unit
) : BaseAdapter() {

    override fun getCount(): Int = tasks.size
    override fun getItem(position: Int): Task = tasks[position]
    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(parent?.context).inflate(R.layout.task_item, parent, false)
        val task = getItem(position)

        val taskText = view.findViewById<TextView>(R.id.taskText)
        val taskCheckBox = view.findViewById<CheckBox>(R.id.taskCheckBox)

        taskText.text = task.name
        taskCheckBox.isChecked = task.isCompleted

        // Detect when clicking on the task name
        taskText.setOnClickListener {
            onTaskClick(task)
        }

        // Detect when the checkbox changes
        taskCheckBox.setOnCheckedChangeListener { _, isChecked ->
            onTaskCheckChanged(task, isChecked)
        }

        return view
    }
}
