// File: OrderViewModel.kt
package com.example.refurbi.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.refurbi.data.local.AppDatabase
import com.example.refurbi.data.local.OrderEntity
import com.example.refurbi.data.local.OrderItemEntity
import com.example.refurbi.data.repository.OrderRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class OrderViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: OrderRepository

    init {
        val db = AppDatabase.getDatabase(application)
        repository = OrderRepository(db.orderDao(), db.orderItemDao())
    }

    private val _orders = MutableStateFlow<List<OrderEntity>>(emptyList())
    val orders: StateFlow<List<OrderEntity>> = _orders

    fun placeOrder(userId: Int, totalPrice: Double, items: List<OrderItemEntity>) {
        viewModelScope.launch {
            val order = OrderEntity(
                userId = userId,
                totalPrice = totalPrice,
                date = System.currentTimeMillis()
            )
            repository.placeOrder(order, items)
            // Optionally, refresh orders after placing one.
            loadOrdersForUser(userId)
        }
    }

    fun loadOrdersForUser(userId: Int) {
        viewModelScope.launch {
            val orderList = repository.getOrdersForUser(userId)
            _orders.value = orderList
        }
    }
}
