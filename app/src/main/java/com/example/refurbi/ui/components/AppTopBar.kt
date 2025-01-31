// File: AppTopBar.kt
package com.example.refurbi.ui.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun AppTopBar(
    title: String,
    showBackButton: Boolean = false,
    onBack: (() -> Unit)? = null
) {
    TopAppBar(
        title = { Text(title) },
        navigationIcon = if (showBackButton && onBack != null) {
            {
                IconButton(onClick = onBack) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
            }
        } else null,
        elevation = 8.dp
    )
}
