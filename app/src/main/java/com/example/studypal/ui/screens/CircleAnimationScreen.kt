package com.example.studypal.ui.screens

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.studypal.ui.components.PrimaryButton

@Composable
fun CircleAnimationScreen(onBackToHome: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    // Animación del tamaño del círculo
    val circleSize by animateDpAsState(
        targetValue = if (expanded) 200.dp else 80.dp,
        label = "circleAnimation"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(circleSize)
                .background(Color(0xFF3F51B5), shape = CircleShape)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = { expanded = !expanded }) {
            Text(if (expanded) "Reducir" else "Expandir")
        }

        Spacer(modifier = Modifier.height(24.dp))

        PrimaryButton(text = "Volver al inicio", onClick = onBackToHome)
    }
}
