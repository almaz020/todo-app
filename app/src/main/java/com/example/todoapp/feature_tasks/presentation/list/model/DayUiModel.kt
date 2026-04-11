package com.example.todoapp.feature_tasks.presentation.list.model

data class DayUiModel(
    val timestamp: Long,
    val dayOfMonth: String,
    val dayOfWeek: String,
    val isSelected: Boolean
)