package com.example.todoapp.feature_tasks.domain.model

data class Task(
    val id: Int,
    val dateStart: Long,
    val dateEnd: Long,
    val name: String,
    val description: String
)