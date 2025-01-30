package com.example.refurbi.presentation.ui.navigation.tabs.main

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.example.refurbi.AppContent

class MainScreen(val userEmail: String?) : Screen {
    @Composable
    override fun Content() {
        AppContent()
        println("User Email: $userEmail")
    }
}