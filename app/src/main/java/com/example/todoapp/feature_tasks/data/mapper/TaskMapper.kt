package com.example.todoapp.feature_tasks.data.mapper

import com.example.todoapp.feature_tasks.domain.model.Task

fun TaskEntity.toDomain() = Task(
    id = id,
    dateStart = dateStart,
    dateEnd = dateEnd,
    name = name,
    description = description
)

fun Task.toEntity() = TaskEntity(
    id = id,
    dateStart = dateStart,
    dateEnd = dateEnd,
    name = name,
    description = description
)