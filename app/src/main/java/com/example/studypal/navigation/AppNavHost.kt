package com.example.studypal.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.studypal.ui.screens.AddTaskScreen
import com.example.studypal.ui.screens.HomeScreen
import com.example.studypal.ui.screens.SettingsScreen
import com.example.studypal.ui.screens.TasksScreen
import com.example.studypal.ui.screens.CircleAnimationScreen

object Routes {
    const val HOME = "home"
    const val TASKS = "tasks"
    const val ADD_TASK = "add_task"
    const val SETTINGS = "settings"
}

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Routes.HOME
    ) {
        composable(Routes.HOME) {
            HomeScreen(onNavigate = { route -> navController.navigate(route) })
        }

        composable(Routes.TASKS) {
            TasksScreen(
                onNavigateToAddTask = { navController.navigate(Routes.ADD_TASK) },
                onNavigateToSettings = { navController.navigate(Routes.SETTINGS) },
                onBack = { navController.popBackStack() }
            )
        }

        composable(Routes.ADD_TASK) {
            AddTaskScreen(onTaskSaved = { navController.popBackStack() })
        }

        composable(Routes.SETTINGS) {
            SettingsScreen(onNavigate = { navController.navigate(Routes.HOME) })
        }

        composable("circle") {
            CircleAnimationScreen(onBackToHome = { navController.navigate(Routes.HOME) })
        }
    }
}
