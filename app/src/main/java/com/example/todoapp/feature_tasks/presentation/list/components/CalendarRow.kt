package com.example.todoapp.feature_tasks.presentation.list.components

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.todoapp.feature_tasks.presentation.list.model.DayUiModel

@Composable
fun CalendarRow(
    days: List<DayUiModel>,
    onDateClick: (Long) -> Unit
) {
    LazyRow {
        items(days) { day ->
            DayItem(day, onDateClick)
        }
    }
}