package com.example.todoapp.feature_tasks.presentation.list.model

import com.example.todoapp.feature_tasks.domain.model.Task

data class HourSlotUiModel(
    val startTime: Long,
    val endTime: Long,
    val hourText: String,
    val tasks: List<Task>
)