package com.example.todoapp.core.database.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val dateStart: Long,
    val dateEnd: Long,
    val name: String,
    val description: String
)