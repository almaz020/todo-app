package com.example.todoapp.feature_tasks.di

import com.example.todoapp.feature_tasks.data.repository.TaskRepositoryImpl
import com.example.todoapp.feature_tasks.domain.repository.TaskRepository
import com.example.todoapp.feature_tasks.domain.usecase.GetTasksForDayUseCase

val taskModule = module {

    // Repository
    single<TaskRepository> {
        TaskRepositoryImpl(get())
    }

    // UseCases
    factory {
        GetTasksForDayUseCase(get())
    }
}