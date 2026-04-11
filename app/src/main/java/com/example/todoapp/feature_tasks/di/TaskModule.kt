package com.example.todoapp.feature_tasks.di

import com.example.todoapp.feature_tasks.data.repository.TaskRepositoryImpl
import com.example.todoapp.feature_tasks.domain.repository.TaskRepository
import com.example.todoapp.feature_tasks.domain.usecase.CreateTaskUseCase
import com.example.todoapp.feature_tasks.domain.usecase.GetTaskByIdUseCase
import com.example.todoapp.feature_tasks.domain.usecase.GetTasksForDayUseCase
import com.example.todoapp.feature_tasks.presentation.create.CreateTaskViewModel
import com.example.todoapp.feature_tasks.presentation.detail.TaskDetailViewModel
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

    factory {
        GetTaskByIdUseCase(get())
    }

    viewModel {
        TaskDetailViewModel(get())
    }

    factory {
        CreateTaskUseCase(get())
    }

    viewModel {
        CreateTaskViewModel(get())
    }
}