package com.example.todoapp.app

import android.content.Context
import com.example.todoapp.core.database.local.dao.TaskDao
import com.example.todoapp.core.database.local.entity.TaskEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlin.collections.map

class DataLoader(
    private val context: Context,
    private val dao: TaskDao
) {

    suspend fun loadInitialData() {
        val existing = dao.getAll()
        if (existing.isNotEmpty()) return

        val json = context.assets
            .open("tasks.json")
            .bufferedReader()
            .use { it.readText() }

        val gson = Gson()

        val type = object : TypeToken<List<TaskEntity>>() {}.type
        val tasks: List<TaskEntity> = gson.fromJson(json, type)

        dao.insertTasks(tasks)
    }
}