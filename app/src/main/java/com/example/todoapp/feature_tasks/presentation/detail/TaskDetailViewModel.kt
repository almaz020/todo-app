package com.example.todoapp.feature_tasks.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.feature_tasks.domain.model.Task
import com.example.todoapp.feature_tasks.domain.usecase.GetTaskByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TaskDetailViewModel(
    private val getTaskByIdUseCase: GetTaskByIdUseCase
) : ViewModel() {

    private val _task = MutableStateFlow<Task?>(null)
    val task: StateFlow<Task?> = _task

    fun loadTask(taskId: Int?) {
        if (taskId == null) return

        viewModelScope.launch {
            _task.value = getTaskByIdUseCase(taskId)
        }
    }
}