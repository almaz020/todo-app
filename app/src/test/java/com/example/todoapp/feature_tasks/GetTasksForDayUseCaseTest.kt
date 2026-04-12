package com.example.todoapp.feature_tasks

import com.example.todoapp.feature_tasks.domain.model.Task
import com.example.todoapp.feature_tasks.domain.usecase.GetTasksForDayUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetTasksForDayUseCaseTest {

    private lateinit var useCase: GetTasksForDayUseCase
    private lateinit var fakeRepository: FakeTaskRepository

    @Before
    fun setup() {
        fakeRepository = FakeTaskRepository()
        useCase = GetTasksForDayUseCase(fakeRepository)
    }

    @Test
    fun `should return tasks only for selected day`() = runBlocking {

        val dayStart = 1000L
        val dayEnd = 2000L

        val taskInDay = Task(
            id = 1,
            name = "Task 1",
            description = "",
            dateStart = 1200L,
            dateEnd = 1500L
        )

        val taskOutside = Task(
            id = 2,
            name = "Task 2",
            description = "",
            dateStart = 3000L,
            dateEnd = 4000L
        )

        fakeRepository.tasks = listOf(taskInDay, taskOutside)

        val result = useCase(dayStart, dayEnd)

        assert(result.size == 1)
        assert(result.first().id == 1)
    }
}