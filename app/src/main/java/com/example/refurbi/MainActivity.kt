package com.example.refurbi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import com.example.refurbi.navigation.NavGraph
import com.example.refurbi.ui.theme.RefurbiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RefurbiTheme {
                // Set up the Navigation Graph
                NavGraph()
            }
        }
    }
}
