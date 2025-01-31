package com.example.refurbi.ui.screens


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.refurbi.data.model.Product
import com.example.refurbi.navigation.Screen
import com.example.refurbi.viewmodel.ProductViewModel

@Composable
fun ProductCatalogScreen(
    navController: NavController,
    viewModel: ProductViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val productList = viewModel.productList.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Product Catalog") }) }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            items(productList.value) { product ->
                ProductItem(product = product, onClick = {
                    navController.navigate("${Screen.ProductDetails.route}/${product.id}")
                })
            }
        }
    }
}

@Composable
fun ProductItem(product: Product, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(product.name)
            Spacer(modifier = Modifier.height(4.dp))
            Text("Price: \$${product.price}")
        }
    }
}
