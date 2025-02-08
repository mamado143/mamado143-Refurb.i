// File: PaymentScreen.kt
package com.example.refurbi.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentScreen(
    navController: NavController,
    totalPrice: Double,
    onPaymentSuccess: () -> Unit,
    onPaymentFailure: (String) -> Unit
) {
    // For simplicity, we'll simulate payment processing.
    var isProcessing by remember { mutableStateOf(false) }
    var paymentMessage by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            SmallTopAppBar(title = { Text("Payment") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text("Total Payment: \$${totalPrice}", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    isProcessing = true
                    // Simulate a payment delay.
                    // In a real integration, you'd call Stripe's API here.
                    kotlinx.coroutines.GlobalScope.launch {
                        kotlinx.coroutines.delay(2000)
                        isProcessing = false
                        // Simulate success:
                        onPaymentSuccess()
                    }
                },
                enabled = !isProcessing,
                modifier = Modifier.fillMaxWidth()
            ) {
                if (isProcessing) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(24.dp)
                    )
                } else {
                    Text("Pay Now")
                }
            }
            if (paymentMessage.isNotEmpty()) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(paymentMessage, color = MaterialTheme.colorScheme.error)
            }
        }
    }
}
