package com.example.todoapp.feature_tasks.presentation.list.utils

import com.example.todoapp.feature_tasks.domain.model.Task
import com.example.todoapp.feature_tasks.presentation.list.model.HourSlotUiModel
import java.util.Calendar

fun generateDaySlots(
    date: Long,
    tasks: List<Task>
): List<HourSlotUiModel> {
    val calendar = Calendar.getInstance().apply {
        timeInMillis = date
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }
    val startOfDay = calendar.timeInMillis

    return (0 until 24).map { hour ->
        val start = startOfDay + hour * 3_600_000L
        val end = start + 3_600_000L

        val tasksInHour = tasks.filter { task ->
            val taskStartMinute = task.dateStart / 60000
            val taskEndMinute = task.dateEnd / 60000
            val slotStartMinute = start / 60000
            val slotEndMinute = end / 60000

            taskStartMinute < slotEndMinute && taskEndMinute > slotStartMinute
        }

        HourSlotUiModel(
            startTime = start,
            endTime = end,
            hourText = String.format("%02d:00 - %02d:00", hour, hour + 1),
            tasks = tasksInHour
        )
    }
}