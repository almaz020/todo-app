package com.example.todoapp.feature_tasks.presentation.list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun TaskListScreen(
    viewModel: TaskListViewModel = org.koin.androidx.compose.koinViewModel()
) {
    val tasks by viewModel.tasks.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadTasksForDate(System.currentTimeMillis())
    }

    LazyColumn {
        items(tasks) { task ->
            Text(text = task.name)
        }
    }
}