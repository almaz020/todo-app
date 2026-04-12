package com.example.todoapp.feature_tasks.presentation.list.utils

import com.example.todoapp.feature_tasks.presentation.list.model.DayUiModel
import java.util.Calendar

fun generateDays(
    selectedDate: Long
): List<DayUiModel> {

    val calendar = Calendar.getInstance()
    calendar.timeInMillis = selectedDate

    val days = mutableListOf<DayUiModel>()

    // покажем 7 дней до и 7 после
    for (i in -7..7) {
        val cal = calendar.clone() as Calendar
        cal.add(Calendar.DAY_OF_MONTH, i)

        val isSelected = i == 0

        days.add(
            DayUiModel(
                timestamp = cal.timeInMillis,
                dayOfMonth = cal.get(Calendar.DAY_OF_MONTH).toString(),
                dayOfWeek = getDayOfWeek(cal.get(Calendar.DAY_OF_WEEK)),
                isSelected = isSelected
            )
        )
    }

    return days
}

private fun getDayOfWeek(day: Int): String {
    return when (day) {
        Calendar.MONDAY -> "Пн"
        Calendar.TUESDAY -> "Вт"
        Calendar.WEDNESDAY -> "Ср"
        Calendar.THURSDAY -> "Чт"
        Calendar.FRIDAY -> "Пт"
        Calendar.SATURDAY -> "Сб"
        Calendar.SUNDAY -> "Вс"
        else -> ""
    }
}