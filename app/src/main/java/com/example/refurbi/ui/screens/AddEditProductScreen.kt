package com.example.refurbi.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.refurbi.data.model.Product
import com.example.refurbi.navigation.Screen
import com.example.refurbi.viewmodel.ProductViewModel

@Composable
fun AddEditProductScreen(
    navController: NavController,
    productId: Int = -1, // Default value indicates a new product.
    productViewModel: ProductViewModel = viewModel()
) {
    val isEditing = productId != -1
    val existingProduct = if (isEditing) productViewModel.getProductById(productId) else null

    // Remember states for the form fields.
    var name by remember { mutableStateOf(existingProduct?.name ?: "") }
    var description by remember { mutableStateOf(existingProduct?.description ?: "") }
    var price by remember { mutableStateOf(existingProduct?.price?.toString() ?: "") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (isEditing) "Edit Product" else "Add Product") }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Product Name") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = price,
                onValueChange = { price = it },
                label = { Text("Price") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    val priceDouble = price.toDoubleOrNull() ?: 0.0
                    if (isEditing && existingProduct != null) {
                        // Update the product.
                        productViewModel.updateProduct(
                            existingProduct.copy(
                                name = name,
                                description = description,
                                price = priceDouble
                            )
                        )
                    } else {
                        // Add a new product.
                        productViewModel.addProduct(
                            Product(
                                id = 0, // The id will be generated in addProduct().
                                name = name,
                                description = description,
                                price = priceDouble
                            )
                        )
                    }
                    // After saving, navigate back to the Admin Panel.
                    navController.navigate(Screen.AdminPanel.route) {
                        popUpTo(Screen.AdminPanel.route) { inclusive = true }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save")
            }
        }
    }
}
