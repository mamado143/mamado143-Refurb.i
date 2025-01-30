package org.refurbi.app.domain.model.cart


import kotlinx.serialization.SerialName

@Serializable
data class CartItem(
    @SerialName("cartId")
    val cartId: Int,
    @SerialName("productId")
    val productId: Int = 0,
    @SerialName("quantity")
    val quantity: Int = 0,
    @SerialName("userId")
    val userId: Int = 0
)