package com.example.studypal.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.studypal.R
import com.example.studypal.ui.components.PrimaryButton

data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val subject: String,
    val iconRes: Int
)

@Composable
fun TasksScreen(
    onNavigateToAddTask: () -> Unit,
    onNavigateToSettings: () -> Unit,
    onBack: () -> Unit
) {
    val sampleTasks = List(20) { index ->
        Task(
            id = index + 1,
            title = "Tarea ${index + 1}",
            description = "Descripción breve de la tarea ${index + 1}.",
            subject = "Curso ${index % 5 + 1}",
            iconRes = R.drawable.ic_launcher_foreground // Usa tu recurso de imagen o avatar
        )
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Mis Tareas 📝",
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(sampleTasks) { task ->
                    TaskItem(task = task)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            PrimaryButton(text = "Agregar nueva tarea", onClick = onNavigateToAddTask)
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = onNavigateToSettings, modifier = Modifier.fillMaxWidth()) {
                Text("Ir a configuración")
            }
        }
    }
}

@Composable
fun TaskItem(task: Task) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(3.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = task.iconRes),
                contentDescription = "Icono de tarea",
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text("ID: ${task.id}", style = MaterialTheme.typography.labelSmall)
                Text(task.title, fontWeight = FontWeight.Bold)
                Text(task.description)
                Text("Asignatura: ${task.subject}", style = MaterialTheme.typography.labelSmall)
            }
        }
    }
}