package com.example.refurbi.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.refurbi.ui.components.AppTopBar

@Composable
fun OrderConfirmationScreen(navController: NavController) {
    Scaffold(
        topBar = {
            AppTopBar(
                title = "Order Confirmation",
                showBackButton = true,
                onBack = { navController.popBackStack() }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Text("Your order has been placed successfully!", style = MaterialTheme.typography.headlineSmall)
        }
    }
}
