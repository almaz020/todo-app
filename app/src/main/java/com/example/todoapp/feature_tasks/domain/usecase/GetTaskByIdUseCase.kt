package com.example.todoapp.feature_tasks.domain.usecase

import com.example.todoapp.feature_tasks.domain.model.Task
import com.example.todoapp.feature_tasks.domain.repository.TaskRepository

class GetTaskByIdUseCase(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(id: Int): Task? {
        return repository.getAllTasks().find { it.id == id }
    }
}