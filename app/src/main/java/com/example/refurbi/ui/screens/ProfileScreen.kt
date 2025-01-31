// File: ProfileScreen.kt
package com.example.refurbi.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.refurbi.navigation.Screen
import com.example.refurbi.ui.components.AppTopBar
import com.example.refurbi.ui.components.AppBottomBar
import com.example.refurbi.viewmodel.AuthViewModel

@Composable
fun ProfileScreen(navController: NavController, authViewModel: AuthViewModel = viewModel()) {
    val currentUser = authViewModel.currentUser.value

    Scaffold(
        topBar = { AppTopBar(title = "Profile", showBackButton = false) },
        bottomBar = { AppBottomBar(navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (currentUser != null) {
                Text("Welcome, ${currentUser.name}!", style = MaterialTheme.typography.h5)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Email: ${currentUser.email}", style = MaterialTheme.typography.body1)
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        authViewModel.logout()
                        navController.navigate(Screen.Login.route) {
                            popUpTo(Screen.Home.route) { inclusive = true }
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Logout")
                }
            } else {
                Text("No user is logged in.", style = MaterialTheme.typography.h6)
            }
        }
    }
}
