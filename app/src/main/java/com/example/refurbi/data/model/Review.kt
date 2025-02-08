// File: Review.kt
package com.example.refurbi.data.model

data class Review(
    val productId: Int,
    val userId: Int,
    val rating: Float,
    val comment: String
)
