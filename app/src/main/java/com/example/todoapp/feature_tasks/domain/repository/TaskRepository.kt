package com.example.todoapp.feature_tasks.domain.repository

import com.example.todoapp.feature_tasks.domain.model.Task

interface TaskRepository {

    suspend fun getTasksForDay(
        startOfDay: Long,
        endOfDay: Long
    ): List<Task>

    suspend fun getAllTasks(): List<Task>

    suspend fun createTask(task: Task)

    suspend fun deleteTask(task: Task)
}