package com.example.todoapp.feature_tasks.presentation.list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.todoapp.feature_tasks.domain.model.Task
import com.example.todoapp.feature_tasks.presentation.list.utils.formatTime

@Composable
fun TaskItem(
    task: Task,
    onClick: (Task) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFBBDEFB))
            .clickable { onClick(task) }
            .padding(12.dp)
    ) {

        Text(
            text = task.name,
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = formatTime(task.dateStart, task.dateEnd),
            style = MaterialTheme.typography.bodySmall,
            color = Color.DarkGray
        )
    }
}