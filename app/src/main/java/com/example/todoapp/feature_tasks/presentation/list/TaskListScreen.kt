package com.example.todoapp.feature_tasks.presentation.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import com.example.todoapp.feature_tasks.presentation.list.components.CalendarRow
import com.example.todoapp.feature_tasks.presentation.list.components.HourSlotItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun TaskListScreen(
    onTaskClick: (Int) -> Unit,
    viewModel: TaskListViewModel = koinViewModel(),
    onCreateClick: () -> Unit
) {
    val days by viewModel.days.collectAsState()
    val slots by viewModel.slots.collectAsState()

    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(lifecycleOwner) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
            viewModel.loadTasksForDate(viewModel.selectedDate.value)
        }
    }


    Box(modifier = Modifier.fillMaxSize().padding(vertical = 26.dp, horizontal = 6.dp)) {

        Column(modifier = Modifier.fillMaxSize()) {
            CalendarRow(
                days = days,
                onDateClick = viewModel::onDateSelected
            )

            HorizontalDivider(
                modifier = Modifier,
                thickness = DividerDefaults.Thickness,
                color = DividerDefaults.color
            )

            LazyColumn {
                items(slots) { slot ->
                    HourSlotItem(
                        slot = slot,
                        onTaskClick = { task -> onTaskClick(task.id) }
                    )
                }
            }
        }

        FloatingActionButton(
            onClick = onCreateClick,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Text("+")
        }
    }
}