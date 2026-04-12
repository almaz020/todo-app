package com.example.todoapp.feature_tasks.presentation.list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.todoapp.feature_tasks.domain.model.Task
import com.example.todoapp.feature_tasks.presentation.list.model.HourSlotUiModel

@Composable
fun HourSlotItem(
    slot: HourSlotUiModel,
    onTaskClick: (Task) -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .padding(vertical = 4.dp)
    ) {

        // Время слева
        Text(
            text = slot.hourText,
            modifier = Modifier
                .width(90.dp)
                .padding(start = 8.dp, top = 8.dp),
            style = MaterialTheme.typography.bodySmall
        )

        // Вертикальная линия (как в календарях)
        Box(
            modifier = Modifier
                .width(1.dp)
                .fillMaxHeight()
                .background(Color.LightGray)
        )

        // Контент задач
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(8.dp)
        ) {

            if (slot.tasks.isEmpty()) {
                Spacer(modifier = Modifier.height(24.dp))
            } else {
                slot.tasks.forEach { task ->
                    TaskItem(task, onTaskClick)
                }
            }
        }
    }
}