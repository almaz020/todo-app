package com.example.todoapp.feature_tasks.data.repository

import com.example.todoapp.feature_tasks.data.mapper.toEntity
import com.example.todoapp.feature_tasks.domain.repository.TaskRepository
import com.example.todoapp.feature_tasks.domain.model.Task

class TaskRepositoryImpl(
    private val dao: TaskDao
) : TaskRepository {

    override suspend fun getTasksForDay(
        startOfDay: Long,
        endOfDay: Long
    ): List<Task> {
        return dao.getTasksForDay(startOfDay, endOfDay)
            .map { it.toDomain() }
    }

    override suspend fun getAllTasks(): List<Task> {
        return dao.getAll().map { it.toDomain() }
    }

    override suspend fun createTask(task: Task) {
        dao.insertTask(task.toEntity())
    }

    override suspend fun deleteTask(task: Task) {
        // добавим позже
    }
}