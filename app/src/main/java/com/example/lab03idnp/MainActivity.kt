package com.example.lab03idnp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.lab03idnp.navigation.AppNavHost
import com.example.lab03idnp.ui.theme.Lab03IDNPTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab03IDNPTheme {
                AppNavHost()
            }
        }
    }
}
