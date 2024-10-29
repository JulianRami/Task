package com.example.task.data.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TaskDao {
    @Insert
    suspend fun insert(task: Task): Long

    @Query("SELECT * FROM tasks WHERE isCompleted = :isCompleted")
    fun getTasks(isCompleted: Boolean): LiveData<List<Task>>

    @Update
    suspend fun update(task: Task)
}
