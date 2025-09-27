package com.example.lab03idnp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lab03idnp.ui.screens.FirstScreen
import com.example.lab03idnp.ui.screens.SecondScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "screen1") {
        composable("screen1") {
            FirstScreen(onNavigate = { message ->
                navController.navigate("screen2/$message")
            })
        }
        composable("screen2/{text}") { backStackEntry ->
            val text = backStackEntry.arguments?.getString("text") ?: ""
            SecondScreen(
                text = text,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
