package com.example.refurbi.data.repository

import com.example.refurbi.data.local.OrderDao
import com.example.refurbi.data.local.OrderEntity
import com.example.refurbi.data.local.OrderItemDao
import com.example.refurbi.data.local.OrderItemEntity

class OrderRepository(
    private val orderDao: OrderDao,
    private val orderItemDao: OrderItemDao
) {
    suspend fun placeOrder(order: OrderEntity, items: List<OrderItemEntity>) {
        // Insert order and then get the new order ID.
        val orderId = orderDao.insertOrder(order).toInt()
        // Associate order items with the orderId
        val itemsWithOrderId = items.map { it.copy(orderId = orderId) }
        orderItemDao.insertOrderItems(itemsWithOrderId)
    }

    suspend fun getOrdersForUser(userId: Int): List<OrderEntity> {
        return orderDao.getOrdersForUser(userId)
    }

    suspend fun getOrderItems(orderId: Int): List<OrderItemEntity> {
        return orderItemDao.getOrderItems(orderId)
    }
}
