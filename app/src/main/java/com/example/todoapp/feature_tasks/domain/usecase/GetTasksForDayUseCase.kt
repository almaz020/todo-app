package com.example.todoapp.feature_tasks.domain.usecase

import com.example.todoapp.feature_tasks.domain.model.Task
import com.example.todoapp.feature_tasks.domain.repository.TaskRepository

class GetTasksForDayUseCase(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(
        start: Long,
        end: Long
    ): List<Task> {
        return repository.getTasksForDay(start, end)
    }
}