// File: HomeScreen.kt
package com.example.refurbi.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.refurbi.ui.components.AppTopBar
import com.example.refurbi.ui.components.AppBottomBar
import com.example.refurbi.ui.components.BottomBarScreen

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = { AppTopBar(title = "Refurb.i Home", showBackButton = false) },
        bottomBar = { AppBottomBar(navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Welcome to Refurb.i")
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navController.navigate(BottomBarScreen.Catalog.route) }) {
                Text("Browse Products")
            }
        }
    }
}
