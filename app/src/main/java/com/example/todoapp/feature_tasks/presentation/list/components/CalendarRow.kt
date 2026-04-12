package com.example.todoapp.feature_tasks.presentation.list.components

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.todoapp.feature_tasks.presentation.list.model.DayUiModel

@Composable
fun CalendarRow(
    days: List<DayUiModel>,
    onDateClick: (Long) -> Unit
) {

    val listState = rememberLazyListState()

    val selectedIndex = days.indexOfFirst { it.isSelected }

    LaunchedEffect(selectedIndex) {
        if (selectedIndex >= 0) {
            listState.animateScrollToItem(
                index = selectedIndex,
                scrollOffset = -200 // подбирается
            )
        }
    }

    LazyRow(
        state = listState
    ) {
        items(days) { day ->
            DayItem(day, onDateClick)
        }
    }
}