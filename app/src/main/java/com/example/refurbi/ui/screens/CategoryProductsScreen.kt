package com.example.refurbi.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.refurbi.navigation.Screen
import com.example.refurbi.ui.components.AppTopBar
import com.example.refurbi.viewmodel.ProductViewModel

@Composable
fun CategoryProductsScreen(
    navController: NavController,
    categoryId: Int,
    productViewModel: ProductViewModel = viewModel()
) {
    // For demonstration purposes, we filter products using a simple dummy condition.
    val productList by productViewModel.productList.collectAsState()
    val filteredProducts = productList.filter { product ->
        // Replace this with your actual product-category mapping.
        product.id % 4 == categoryId % 4
    }

    Scaffold(
        topBar = {
            AppTopBar(
                title = "Products",
                showBackButton = true,
                onBack = { navController.popBackStack() }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            items(filteredProducts) { product ->
                ProductItem(product = product, onClick = {
                    navController.navigate("${Screen.ProductDetails.route}/${product.id}")
                })
            }
        }
    }
}
