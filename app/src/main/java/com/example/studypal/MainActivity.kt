package com.example.studypal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.studypal.navigation.AppNavHost
import com.example.studypal.ui.theme.StudyPalTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudyPalTheme {
                AppNavHost()
            }
        }
    }
}
