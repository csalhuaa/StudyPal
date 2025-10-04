package com.example.studypal.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.studypal.ui.screens.HomeScreen
import com.example.studypal.ui.screens.SettingsScreen
import com.example.studypal.ui.screens.TasksScreen

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(onNavigate = { navController.navigate("tasks") })
        }
        composable("tasks") {
            TasksScreen(onNavigate = { navController.navigate("settings") })
        }
        composable("settings") {
            SettingsScreen(onNavigate = { navController.navigate("home") })
        }
    }
}
