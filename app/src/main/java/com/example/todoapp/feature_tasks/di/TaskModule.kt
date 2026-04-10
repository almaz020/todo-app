package com.example.todoapp.feature_tasks.di

import com.example.todoapp.feature_tasks.data.repository.TaskRepositoryImpl
import com.example.todoapp.feature_tasks.domain.repository.TaskRepository
import com.example.todoapp.feature_tasks.domain.usecase.GetTasksForDayUseCase
import com.example.todoapp.feature_tasks.presentation.list.TaskListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val taskModule = module {

    // Repository
    single<TaskRepository> {
        TaskRepositoryImpl(get())
    }

    // UseCases
    factory {
        GetTasksForDayUseCase(get())
    }

    viewModel {
        TaskListViewModel(get())
    }
}