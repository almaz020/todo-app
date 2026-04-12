package com.example.todoapp.feature_tasks

import com.example.todoapp.feature_tasks.domain.model.Task
import com.example.todoapp.feature_tasks.domain.repository.TaskRepository

class FakeTaskRepository : TaskRepository {

    var tasks = listOf<Task>()

    override suspend fun getAllTasks(): List<Task> {
        return tasks
    }

    override suspend fun getTasksForDay(start: Long, end: Long): List<Task> {
        return tasks.filter {
            it.dateStart < end && it.dateEnd > start
        }
    }

    override suspend fun createTask(task: Task) {}
    override suspend fun deleteTask(task: Task) {}
}