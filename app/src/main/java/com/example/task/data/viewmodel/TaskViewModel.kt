package com.example.task.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task.data.model.Task
import com.example.task.data.model.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val repository: TaskRepository
) : ViewModel() {

    val tasks = repository.getTasks(isCompleted = false)
    val completedTasks = repository.getTasks(isCompleted = true)

    private val _selectedTask = MutableLiveData<Task>()
    val selectedTask: LiveData<Task> get() = _selectedTask

    fun selectTask(task: Task) {
        _selectedTask.value = task
    }

    fun addTask(taskName: String) {
        viewModelScope.launch {
            repository.addTask(Task(name = taskName, isCompleted = false))
        }
    }

    fun updateTaskCompletion(task: Task, isCompleted: Boolean) {
        viewModelScope.launch {
            task.isCompleted = isCompleted
            repository.updateTask(task)
        }
    }
}


