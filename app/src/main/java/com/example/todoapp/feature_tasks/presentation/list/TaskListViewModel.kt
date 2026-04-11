package com.example.todoapp.feature_tasks.presentation.list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.feature_tasks.domain.model.Task
import com.example.todoapp.feature_tasks.domain.usecase.GetTasksForDayUseCase
import com.example.todoapp.feature_tasks.presentation.list.model.DayUiModel
import com.example.todoapp.feature_tasks.presentation.list.model.HourSlotUiModel
import com.example.todoapp.feature_tasks.presentation.list.utils.generateDaySlots
import com.example.todoapp.feature_tasks.presentation.list.utils.generateDays
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Calendar

class TaskListViewModel(
    private val getTasksForDayUseCase: GetTasksForDayUseCase
) : ViewModel() {

    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks

    private val _selectedDate = MutableStateFlow(System.currentTimeMillis())
    val selectedDate: StateFlow<Long> = _selectedDate

    private val _days = MutableStateFlow<List<DayUiModel>>(emptyList())
    val days: StateFlow<List<DayUiModel>> = _days

    private val _slots = MutableStateFlow<List<HourSlotUiModel>>(emptyList())
    val slots: StateFlow<List<HourSlotUiModel>> = _slots

    init {
        updateDays()
        loadTasksForDate(_selectedDate.value)
        viewModelScope.launch {
            val allTasks = getTasksForDayUseCase(0, Long.MAX_VALUE)
            Log.d("DEBUG_ALL", "ALL TASKS = ${allTasks.size}")
        }
    }


    fun loadTasksForDate(date: Long) {
        val start = startOfDay(date)
        val end = endOfDay(date)

        viewModelScope.launch {
            val tasks = getTasksForDayUseCase(start, end)
            Log.d("TaskListVM", "Loaded tasks for $date: ${tasks.size}")
            _tasks.value = tasks
            _slots.value = generateDaySlots(date, tasks)
        }
    }


    private fun startOfDay(timestamp: Long): Long {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timestamp
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar.timeInMillis
    }

    private fun endOfDay(timestamp: Long): Long {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timestamp
        calendar.set(Calendar.HOUR_OF_DAY, 23)
        calendar.set(Calendar.MINUTE, 59)
        calendar.set(Calendar.SECOND, 59)
        calendar.set(Calendar.MILLISECOND, 999)
        return calendar.timeInMillis
    }

    private fun updateDays() {
        _days.value = generateDays(_selectedDate.value)
    }

    fun onDateSelected(timestamp: Long) {
        _selectedDate.value = timestamp
        updateDays()
        loadTasksForDate(timestamp)
    }
}