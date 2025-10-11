package com.example.studypal.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studypal.ui.components.PrimaryButton

@Composable
fun TasksScreen(
    onNavigateToAddTask: () -> Unit,
    onNavigateToSettings: () -> Unit,
    onBack: () -> Unit
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Mis tareas 📝",
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
                lineHeight = 30.sp
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Aquí podrás visualizar tus tareas y agregar nuevas cuando lo necesites.",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(24.dp))
            PrimaryButton(text = "Agregar nueva tarea", onClick = onNavigateToAddTask)
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = onNavigateToSettings) {
                Text("Ir a configuración")
            }
        }
    }
}
