package com.example.refurbi.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "order_items")
data class OrderItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val orderId: Int,           // The order this item belongs to
    val productId: Int,
    val productName: String,
    val quantity: Int,
    val price: Double           // Price per unit (or snapshot price)
)
