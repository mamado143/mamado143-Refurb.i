package com.example.refurbi.data.model


data class CartItem(
    val product: Product,
    var quantity: Int = 1
)
