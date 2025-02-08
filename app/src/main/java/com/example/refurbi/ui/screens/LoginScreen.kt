package com.example.refurbi.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.refurbi.navigation.Screen
import com.example.refurbi.ui.components.AppTopBar
import com.example.refurbi.ui.components.BottomBarScreen
import com.example.refurbi.viewmodel.AuthViewModel
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(navController: NavController, authViewModel: AuthViewModel = viewModel()) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val scaffoldState = rememberScaffoldState()  // For Snackbar
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { AppTopBar(title = "Login") }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                elevation = 8.dp
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "Welcome Back!", style = MaterialTheme.typography.h5)
                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Email") },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Password") },
                        modifier = Modifier.fillMaxWidth(),
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            if (authViewModel.login(email, password)) {
                                val role = authViewModel.currentUser.value?.role ?: "customer"
                                if (role == "admin") {
                                    navController.navigate(Screen.AdminPanel.route)
                                } else {
                                    navController.navigate(BottomBarScreen.Home.route)
                                }
                            } else {
                                coroutineScope.launch {
                                    scaffoldState.snackbarHostState.showSnackbar(
                                        message = "Invalid email or password",
                                        actionLabel = "Dismiss"
                                    )
                                }
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(imageVector = Icons.Filled.Lock, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Login")
                    }
                }
            }
        }
    }
}


// Assuming you have these defined elsewhere in your project
// import com.yourpackage.LoginScreen
// import com.yourpackage.AuthViewModel

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    // Create a dummy NavController for the preview
    val navController = rememberNavController()

    // Create a dummy AuthViewModel for the preview
    val authViewModel: AuthViewModel = viewModel()

    // Now call your LoginScreen composable with the dummy parameters
    LoginScreen(navController = navController, authViewModel = authViewModel)
}