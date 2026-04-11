package com.example.todoapp.feature_tasks.presentation.list.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatTime(start: Long, end: Long): String {
    val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
    return "${sdf.format(Date(start))} - ${sdf.format(Date(end))}"
}