package com.example.refurbi.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.refurbi.navigation.Screen
import com.example.refurbi.viewmodel.ProductViewModel

@Composable
fun AdminPanelScreen(
    navController: NavController,
    productViewModel: ProductViewModel = viewModel()
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Admin Panel") }) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Product Inventory", style = MaterialTheme.typography.h6)
                Button(onClick = { navController.navigate("add_edit_product") }) {
                    Text("Add Product")
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            val productList = productViewModel.productList.collectAsState()
            LazyColumn {
                items(productList.value) { product ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        elevation = 4.dp
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text(product.name, style = MaterialTheme.typography.h6)
                                Text("Price: \$${product.price}")
                            }
                            Button(
                                onClick = {
                                    // Navigate to the edit screen with the product's id.
                                    navController.navigate("add_edit_product?productId=${product.id}")
                                }
                            ) {
                                Text("Edit")
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { navController.navigate(Screen.Home.route) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Back to Home")
            }
        }
    }
}
