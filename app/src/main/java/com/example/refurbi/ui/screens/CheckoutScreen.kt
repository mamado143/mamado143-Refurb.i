package com.example.refurbi.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.refurbi.navigation.Screen
import com.example.refurbi.viewmodel.CartViewModel

@Composable
fun CheckoutScreen(
    navController: NavController,
    cartViewModel: CartViewModel = viewModel()
) {
    val cartItems = cartViewModel.cartItems.collectAsState()
    // Calculate the total price by summing up each product's price multiplied by its quantity.
    val totalPrice = cartItems.value.sumOf { it.product.price * it.quantity }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Checkout") }) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text("Order Summary", style = MaterialTheme.typography.h4)
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn {
                items(cartItems.value) { cartItem ->
                    CartItemRow(cartItem = cartItem)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text("Total Price: \$${totalPrice}", style = MaterialTheme.typography.h5)
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    // Here you can integrate order processing logic (e.g., API calls)
                    cartViewModel.clearCart()
                    // Navigate back to Home after placing the order
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) { inclusive = true }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Place Order")
            }
        }
    }
}
