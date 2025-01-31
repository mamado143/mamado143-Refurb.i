package com.example.refurbi.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.refurbi.data.model.Product

@Composable
fun ProductItem(product: Product, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        elevation = 4.dp
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            if (product.imageUrl.isNotEmpty()) {
                Image(
                    painter = rememberAsyncImagePainter(product.imageUrl),
                    contentDescription = product.name,
                    modifier = Modifier.size(64.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
            }
            Column {
                Text(product.name, style = MaterialTheme.typography.h6)
                Spacer(modifier = Modifier.height(4.dp))
                Text("Price: \$${product.price}")
            }
        }
    }
}
