package com.example.todoapp.core.database.local.dao


import androidx.room.*
import com.example.todoapp.core.database.local.entity.TaskEntity

@Dao
interface TaskDao {

    @Query("""
        SELECT * FROM tasks
        WHERE dateStart < :endOfDay AND dateEnd > :startOfDay
    """)
    suspend fun getTasksForDay(
        startOfDay: Long,
        endOfDay: Long
    ): List<TaskEntity>

    @Query("SELECT * FROM tasks")
    suspend fun getAll(): List<TaskEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: TaskEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTasks(tasks: List<TaskEntity>)

    @Delete
    suspend fun deleteTask(task: TaskEntity)
}