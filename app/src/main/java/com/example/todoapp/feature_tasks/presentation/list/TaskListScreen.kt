package com.example.todoapp.feature_tasks.presentation.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.todoapp.feature_tasks.presentation.list.components.CalendarRow
import com.example.todoapp.feature_tasks.presentation.list.components.HourSlotItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun TaskListScreen(
    onTaskClick: (Int) -> Unit,
    viewModel: TaskListViewModel = koinViewModel()
) {
    val days by viewModel.days.collectAsState()
    val slots by viewModel.slots.collectAsState()

    Column {

        // Календарь
        CalendarRow(
            days = days,
            onDateClick = viewModel::onDateSelected
        )

        HorizontalDivider(Modifier, DividerDefaults.Thickness, DividerDefaults.color)

        // Список часов
        LazyColumn {
            items(slots) { slot ->
                HourSlotItem(
                    slot = slot,
                    onTaskClick = { task ->
                        onTaskClick(task.id)
                    }
                )
            }
        }
    }
}