package com.example.todoapp.feature_tasks.data.repository

import android.content.Context
import com.example.todoapp.core.database.local.dao.TaskDao
import com.example.todoapp.feature_tasks.data.mapper.toDomain
import com.example.todoapp.feature_tasks.data.mapper.toEntity
import com.example.todoapp.feature_tasks.domain.repository.TaskRepository
import com.example.todoapp.feature_tasks.domain.model.Task

class TaskRepositoryImpl(
    private val dao: TaskDao
) : TaskRepository {

    override suspend fun getAllTasks(): List<Task> {
        return dao.getAll().map { it.toDomain() }
    }

    override suspend fun getTasksForDay(start: Long, end: Long): List<Task> {
        return dao.getTasksForDay(start, end)
            .map { it.toDomain() }
    }

    override suspend fun createTask(task: Task) {
        dao.insertTask(task.toEntity())
    }

    override suspend fun deleteTask(task: Task) {
        dao.deleteTask(task.toEntity())
    }
}