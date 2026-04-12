package com.example.todoapp.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todoapp.feature_tasks.presentation.create.CreateTaskScreen
import com.example.todoapp.feature_tasks.presentation.detail.TaskDetailScreen
import com.example.todoapp.feature_tasks.presentation.list.TaskListScreen

@Composable
fun AppNavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "task_list"
    ) {

        composable("task_list") {
            TaskListScreen(
                onTaskClick = { taskId ->
                    navController.navigate("task_detail/$taskId")
                },
                onCreateClick = {
                    navController.navigate("create_task")
                }
            )
        }

        composable("task_detail/{taskId}") { backStackEntry ->

            val taskId = backStackEntry.arguments
                ?.getString("taskId")
                ?.toIntOrNull()

            TaskDetailScreen(
                taskId = taskId,
                onBack = { navController.popBackStack() }
            )
        }
        composable("create_task") {
            CreateTaskScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}