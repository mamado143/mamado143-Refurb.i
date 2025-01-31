package com.example.refurbi.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.refurbi.ui.components.AppTopBar
import com.example.refurbi.ui.components.AppBottomBar
import com.example.refurbi.navigation.Screen
import com.example.refurbi.viewmodel.ProductViewModel

@Composable
fun ProductCatalogScreen(
    navController: NavController,
    productViewModel: ProductViewModel = viewModel()
) {
    var searchQuery by remember { mutableStateOf("") }
    val productList by productViewModel.productList.collectAsState()
    val filteredProducts = productList.filter {
        it.name.contains(searchQuery, ignoreCase = true) ||
                it.description.contains(searchQuery, ignoreCase = true)
    }

    Scaffold(
        topBar = { AppTopBar(title = "Catalog") },
        bottomBar = { AppBottomBar(navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Search Products") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn {
                items(filteredProducts) { product ->
                    ProductItem(product = product, onClick = {
                        navController.navigate("${Screen.ProductDetails.route}/${product.id}")
                    })
                }
            }
        }
    }
}
