package com.example.todoapp.feature_tasks.presentation.list.utils

import com.example.todoapp.feature_tasks.domain.model.Task
import com.example.todoapp.feature_tasks.presentation.list.model.HourSlotUiModel
import java.util.Calendar

fun generateDaySlots(
    date: Long,
    tasks: List<Task>
): List<HourSlotUiModel> {

    val calendar = Calendar.getInstance()
    calendar.timeInMillis = date

    // начало дня
    calendar.set(Calendar.HOUR_OF_DAY, 0)
    calendar.set(Calendar.MINUTE, 0)
    calendar.set(Calendar.SECOND, 0)
    calendar.set(Calendar.MILLISECOND, 0)

    val startOfDay = calendar.timeInMillis

    val slots = mutableListOf<HourSlotUiModel>()

    for (hour in 0 until 24) {

        val start = startOfDay + hour * 60 * 60 * 1000
        val end = start + 60 * 60 * 1000

        val tasksInHour = tasks.filter { task ->
            task.dateStart < end && task.dateEnd > start
        }

        slots.add(
            HourSlotUiModel(
                startTime = start,
                endTime = end,
                hourText = String.format("%02d:00 - %02d:00", hour, hour + 1),
                tasks = tasksInHour
            )
        )
    }

    return slots
}