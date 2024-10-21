package com.example.task.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.task.data.model.Task

class TaskViewModel : ViewModel() {

    private val _tasks = MutableLiveData<MutableList<Task>>(mutableListOf())
    val tasks: LiveData<MutableList<Task>> get() = _tasks
    private val _completedTasks = MutableLiveData<MutableList<Task>>(mutableListOf())
    val completedTasks: LiveData<MutableList<Task>> get() = _completedTasks

    private val _selectedTask = MutableLiveData<Task>()
    val selectedTask: LiveData<Task> get() = _selectedTask

    fun addTask(taskName: String) {
        val task = Task(0, taskName, false)
        _tasks.value?.add(task)
        _tasks.value = _tasks.value
    }

    fun updateTaskCompletion(task: Task, isCompleted: Boolean) {
        task.isCompleted = isCompleted

        if (isCompleted) {
            _tasks.value?.remove(task)
            _completedTasks.value?.add(task)
        } else {
            _completedTasks.value?.remove(task)
            _tasks.value?.add(task)
        }

        _tasks.value = _tasks.value
        _completedTasks.value = _completedTasks.value
    }

    fun selectTask(task: Task) {
        _selectedTask.value = task
    }
}
