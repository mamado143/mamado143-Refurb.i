package com.example.refurbi.data.model

data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: String = ""  // Optional field for image URLs
)
