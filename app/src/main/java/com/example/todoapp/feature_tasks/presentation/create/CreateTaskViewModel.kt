package com.example.todoapp.feature_tasks.presentation.create

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.feature_tasks.domain.model.Task
import com.example.todoapp.feature_tasks.domain.usecase.CreateTaskUseCase
import kotlinx.coroutines.launch

class CreateTaskViewModel(
    private val createTaskUseCase: CreateTaskUseCase
) : ViewModel() {

    var name by mutableStateOf("")
        private set

    var description by mutableStateOf("")
        private set

    var dateStart by mutableLongStateOf(System.currentTimeMillis())
        private set

    var dateEnd by mutableLongStateOf(System.currentTimeMillis() + 60 * 60 * 1000)
        private set

    fun onNameChange(value: String) {
        name = value
    }


    fun onDescriptionChange(value: String) {
        description = value
    }

    fun onDateStartChange(value: Long) {
        dateStart = value
    }

    fun onDateEndChange(value: Long) {
        dateEnd = value
    }

    fun createTask(onSuccess: () -> Unit) {
        if (dateEnd <= dateStart) return

        viewModelScope.launch {
            val task = Task(
                id = 0,
                name = name,
                description = description,
                dateStart = dateStart,
                dateEnd = dateEnd
            )
            createTaskUseCase(task)
            onSuccess()
        }
    }


}