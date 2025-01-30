package com.example.refurbi.presentation.ui.screens.payment.model

@Serializable
data class Order(
    val id: Long,
    val userId: Int,
    val productIds: Int,
    val totalQuantity: String,
    val totalPrice: Int,
    val orderProgress: String,
    val selectedColor: String,
    val paymentType: String,
    val trackingId: String,
    val orderDate: String,
    val deliveryDate: String,
)
