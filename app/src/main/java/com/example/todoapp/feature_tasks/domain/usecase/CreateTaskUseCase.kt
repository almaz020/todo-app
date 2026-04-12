package com.example.todoapp.feature_tasks.domain.usecase

import com.example.todoapp.feature_tasks.domain.model.Task
import com.example.todoapp.feature_tasks.domain.repository.TaskRepository

class CreateTaskUseCase(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(task: Task) {
        repository.createTask(task)
    }
}