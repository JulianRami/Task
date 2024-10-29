package com.example.task.data.model

import androidx.lifecycle.LiveData
import javax.inject.Inject

class TaskRepository @Inject constructor(private val taskDao: TaskDao) {
    fun getTasks(isCompleted: Boolean): LiveData<List<Task>> = taskDao.getTasks(isCompleted)
    suspend fun addTask(task: Task) = taskDao.insert(task)
    suspend fun updateTask(task: Task) = taskDao.update(task)
}
