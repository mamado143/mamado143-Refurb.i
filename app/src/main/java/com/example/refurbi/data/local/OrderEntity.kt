package com.example.refurbi.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orders")
data class OrderEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val userId: Int,             // ID of the user placing the order
    val totalPrice: Double,
    val date: Long               // Store a timestamp (e.g., System.currentTimeMillis())
)
