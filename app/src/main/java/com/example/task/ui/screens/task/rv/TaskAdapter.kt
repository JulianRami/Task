package com.example.task.ui.screens.task.rv

import android.graphics.Paint
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

        if (task.isCompleted) {
            taskText.paintFlags = taskText.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            taskText.paintFlags = taskText.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }

        taskText.setOnClickListener {
            onTaskClick(task)
        }

        taskCheckBox.setOnCheckedChangeListener { _, isChecked ->
            onTaskCheckChanged(task, isChecked)
        }

        return view
    }

}
