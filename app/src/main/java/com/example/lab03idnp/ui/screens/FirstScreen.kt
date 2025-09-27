package com.example.lab03idnp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.lab03idnp.ui.components.PrimaryButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirstScreen(onNavigate: (String) -> Unit) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Pantalla 1") }) }
    ) { innerPadding ->
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            PrimaryButton(
                text = "Ir a Pantalla 2",
                onClick = { onNavigate("Hola StudyPal") }
            )
        }
    }
}