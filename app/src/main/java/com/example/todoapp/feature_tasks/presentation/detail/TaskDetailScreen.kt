package com.example.todoapp.feature_tasks.presentation.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todoapp.feature_tasks.presentation.list.utils.formatTime
import org.koin.androidx.compose.koinViewModel

@Composable
fun TaskDetailScreen(
    taskId: Int?,
    viewModel: TaskDetailViewModel = koinViewModel()
) {

    val task by viewModel.task.collectAsState()

    LaunchedEffect(taskId) {
        viewModel.loadTask(taskId)
    }

    if (task == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Задача не найдена")
        }
        return
    }

    val t = task!!

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = t.name,
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = formatTime(t.dateStart, t.dateEnd),
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = t.description,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}