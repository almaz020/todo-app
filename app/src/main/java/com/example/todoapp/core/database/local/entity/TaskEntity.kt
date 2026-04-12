package com.example.todoapp.core.database.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @SerializedName("date_start")
    val dateStart: Long,

    @SerializedName("date_finish")
    val dateEnd: Long,

    val name: String,
    val description: String
)