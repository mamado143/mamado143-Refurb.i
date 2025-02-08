// File: OrderDao.kt
package com.example.refurbi.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface OrderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder(order: OrderEntity): Long

    @Query("SELECT * FROM orders WHERE userId = :userId ORDER BY date DESC")
    suspend fun getOrdersForUser(userId: Int): List<OrderEntity>
}
