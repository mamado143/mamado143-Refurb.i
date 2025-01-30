package com.example.refurbi.presentation.ui.screens.cart.model

@Serializable
data class ProductDetails(
    val productId: String,
    val imageUrl: String,
    val itemCount: Int,
    val itemPrice: Double,
    val totalPrice: Double,
    val colors: String,
    val title: String
)

