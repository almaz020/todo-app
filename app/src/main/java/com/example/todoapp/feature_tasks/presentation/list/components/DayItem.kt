package com.example.todoapp.feature_tasks.presentation.list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.todoapp.feature_tasks.presentation.list.model.DayUiModel

@Composable
fun DayItem(
    day: DayUiModel,
    onClick: (Long) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(
                if (day.isSelected) Color(0xFF1976D2) else Color.LightGray
            )
            .clickable { onClick(day.timestamp) }
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = day.dayOfWeek)
        Text(text = day.dayOfMonth)
    }
}