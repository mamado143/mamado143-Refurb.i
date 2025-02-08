package com.example.refurbi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.refurbi.navigation.NavGraph
import com.example.refurbi.ui.theme.RefurbiTheme
import com.example.refurbi.viewmodel.AuthViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RefurbiTheme {
                val authViewModel: AuthViewModel = viewModel()
                val startDestination = if (authViewModel.currentUser.value != null) {
                    "home"
                } else {
                    "splash"
                }
                NavGraph(startDestination = startDestination)
            }
        }
    }
}
