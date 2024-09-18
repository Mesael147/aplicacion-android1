package com.example.trabajosdesarrollo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.trabajosdesarrollo.ui.Interfaz
import com.example.trabajosdesarrollo.ui.theme.TrabajosDesarrolloTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrabajosDesarrolloTheme {
                Interfaz()
            }
        }
    }
}