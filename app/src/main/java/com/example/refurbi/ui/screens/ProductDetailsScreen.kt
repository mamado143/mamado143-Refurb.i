package com.example.refurbi.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.refurbi.navigation.Screen
import com.example.refurbi.viewmodel.CartViewModel
import com.example.refurbi.viewmodel.ProductViewModel

@Composable
fun ProductDetailsScreen(
    productId: Int,
    navController: NavController,
    productViewModel: ProductViewModel = viewModel(),
    cartViewModel: CartViewModel = viewModel()
) {
    val product = productViewModel.getProductById(productId)

    Scaffold(
        topBar = { TopAppBar(title = { Text("Product Details") }) }
    ) { paddingValues ->
        product?.let {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                Text(text = it.name, style = MaterialTheme.typography.h4)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Price: \$${it.price}")
                Spacer(modifier = Modifier.height(8.dp))
                Text(it.description)
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        cartViewModel.addToCart(it)
                        // Navigate to the Cart screen after adding the product
                        navController.navigate(Screen.Cart.route)
                    }
                ) {
                    Text("Add to Cart")
                }
            }
        } ?: run {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Product not found.")
            }
        }
    }
}
