package com.example.selfprotectionapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.selfprotectionapp.presentation.HomeScreen
import com.example.selfprotectionapp.ui.theme.SelfProtectionAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SelfProtectionAppTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    HomeScreen()
                }
            }
        }
    }
}