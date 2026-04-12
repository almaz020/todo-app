package com.example.todoapp.feature_tasks

import com.example.todoapp.feature_tasks.domain.model.Task
import com.example.todoapp.feature_tasks.presentation.list.utils.generateDaySlots
import junit.framework.Assert.assertEquals
import org.junit.Test

class TimeSlotsGeneratorTest {

    @Test
    fun `task should appear in only one slot when ends at hour boundary`() {

        val date = 0L

        val task = Task(
            id = 1,
            name = "Test",
            description = "",
            dateStart = 9 * 60 * 60 * 1000L,   // 12:00
            dateEnd = 10 * 60 * 60 * 1000L     // 13:00
        )

        val slots = generateDaySlots(date, listOf(task))

        val slotsWithTask = slots.filter { it.tasks.isNotEmpty() }

        assertEquals(1, slotsWithTask.size)

        assertEquals("12:00 - 13:00", slotsWithTask.first().hourText)

        assertEquals(1, slotsWithTask.first().tasks.first().id)
    }
}