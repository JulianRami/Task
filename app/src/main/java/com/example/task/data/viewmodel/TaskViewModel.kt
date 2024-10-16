package com.example.task.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.task.data.model.Task

class TaskViewModel : ViewModel() {

    private val _tasks = MutableLiveData<MutableList<Task>>(mutableListOf())
    val tasks: LiveData<MutableList<Task>> get() = _tasks
    private val _selectedTask = MutableLiveData<Task>()
    val selectedTask: LiveData<Task> get() = _selectedTask

    fun addTask(taskName: String) {
        val task = Task(0, taskName, false)
        _tasks.value?.add(task)
        _tasks.value = _tasks.value
    }

    fun updateTaskCompletion(task: Task, isCompleted: Boolean) {
        task.isCompleted = isCompleted
        _tasks.value = _tasks.value
    }

    fun selectTask(task: Task) {
        _selectedTask.value = task
    }
}
