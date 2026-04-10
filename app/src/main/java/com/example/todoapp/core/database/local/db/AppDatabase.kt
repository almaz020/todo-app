package com.example.todoapp.core.database.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todoapp.core.database.local.dao.TaskDao
import com.example.todoapp.core.database.local.entity.TaskEntity

@Database(
    entities = [TaskEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}