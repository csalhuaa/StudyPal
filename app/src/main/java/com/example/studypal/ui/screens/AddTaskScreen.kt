package com.example.studypal.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(onTaskSaved: () -> Unit) {
    var title by remember { mutableStateOf(TextFieldValue("")) }
    var description by remember { mutableStateOf(TextFieldValue("")) }

    var selectedPriority by remember { mutableStateOf("Media") }
    var expandedPriority by remember { mutableStateOf(false) }

    var selectedStatus by remember { mutableStateOf("Pendiente") }
    var expandedStatus by remember { mutableStateOf(false) }

    var dueDate by remember { mutableStateOf("") }
    var showDatePicker by remember { mutableStateOf(false) }

    var error by remember { mutableStateOf("") }

    val dateFormatter = remember { SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()) }

    val calendar = Calendar.getInstance()
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = calendar.timeInMillis)

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        datePickerState.selectedDateMillis?.let { millis ->
                            dueDate = dateFormatter.format(Date(millis))
                        }
                        showDatePicker = false
                    }
                ) { Text("Aceptar") }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) { Text("Cancelar") }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

    // --- UI ---
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Agregar nueva tarea ✏️",
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
                lineHeight = 30.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Título de la tarea") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                isError = error.isNotEmpty()
            )
            if (error.isNotEmpty()) {
                Text(
                    text = error,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(top = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Descripción") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // --- Dropdown Prioridad ---
            ExposedDropdownMenuBox(
                expanded = expandedPriority,
                onExpandedChange = { expandedPriority = !expandedPriority }
            ) {
                OutlinedTextField(
                    value = selectedPriority,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Prioridad") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedPriority) },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = expandedPriority,
                    onDismissRequest = { expandedPriority = false }
                ) {
                    listOf("Alta", "Media", "Baja").forEach { priority ->
                        DropdownMenuItem(
                            text = { Text(priority) },
                            onClick = {
                                selectedPriority = priority
                                expandedPriority = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // --- Dropdown Estado ---
            ExposedDropdownMenuBox(
                expanded = expandedStatus,
                onExpandedChange = { expandedStatus = !expandedStatus }
            ) {
                OutlinedTextField(
                    value = selectedStatus,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Estado") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedStatus) },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = expandedStatus,
                    onDismissRequest = { expandedStatus = false }
                ) {
                    listOf("Pendiente", "En progreso", "Completada").forEach { status ->
                        DropdownMenuItem(
                            text = { Text(status) },
                            onClick = {
                                selectedStatus = status
                                expandedStatus = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // --- Selector de fecha ---
            OutlinedTextField(
                value = dueDate,
                onValueChange = {},
                readOnly = true,
                label = { Text("Fecha límite") },
                placeholder = { Text("Seleccionar fecha") },
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    IconButton(onClick = { showDatePicker = true }) {
                        Icon(Icons.Default.DateRange, contentDescription = "Elegir fecha")
                    }
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            // --- Botones ---
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        if (title.text.isBlank()) {
                            error = "El título es obligatorio"
                        } else {
                            error = ""
                            // Aquí se podría guardar la tarea en la base de datos o lista
                            onTaskSaved()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                ) {
                    Text("Guardar")
                }

                OutlinedButton(onClick = { onTaskSaved() }) {
                    Text("Cancelar")
                }
            }
        }
    }
}
