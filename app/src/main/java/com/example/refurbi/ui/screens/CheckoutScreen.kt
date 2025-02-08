// File: CheckoutScreen.kt
package com.example.refurbi.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.refurbi.data.model.CartItem
import com.example.refurbi.navigation.Screen
import com.example.refurbi.viewmodel.CartViewModel
import com.example.refurbi.viewmodel.OrderViewModel
import com.example.refurbi.viewmodel.AuthViewModel
import kotlinx.coroutines.launch

@Composable
fun CheckoutScreen(
    navController: NavController,
    cartViewModel: CartViewModel = viewModel(),
    orderViewModel: OrderViewModel = viewModel(),
    authViewModel: AuthViewModel = viewModel()
) {
    val cartItems by cartViewModel.cartItems.collectAsState()
    val totalPrice = cartItems.sumOf { it.product.price * it.quantity }
    val currentUser = authViewModel.currentUser.value
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Checkout") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text("Order Summary", style = androidx.compose.material3.MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn {
                items(cartItems) { cartItem ->
                    // Reuse your existing CartItemRow or create a similar composable.
                    Text("${cartItem.product.name} x${cartItem.quantity} - \$${cartItem.product.price * cartItem.quantity}")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text("Total Price: \$${totalPrice}", style = androidx.compose.material3.MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    // Ensure that a user is logged in before placing an order.
                    if (currentUser != null) {
                        // Convert cart items to OrderItemEntity objects.
                        val orderItems = cartItems.map { cartItem ->
                            // Initially set orderId = 0; the repository will assign the real orderId.
                            com.example.refurbi.data.local.OrderItemEntity(
                                orderId = 0,
                                productId = cartItem.product.id,
                                productName = cartItem.product.name,
                                quantity = cartItem.quantity,
                                price = cartItem.product.price
                            )
                        }
                        coroutineScope.launch {
                            orderViewModel.placeOrder(
                                userId = currentUser.id,
                                totalPrice = totalPrice,
                                items = orderItems
                            )
                            // Clear the cart after placing the order.
                            cartViewModel.clearCart()
                            // Navigate to an order confirmation screen or back to Home.
                            navController.navigate(Screen.Home.route) {
                                popUpTo(Screen.Home.route) { inclusive = true }
                            }
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Place Order")
            }
        }
    }
}
