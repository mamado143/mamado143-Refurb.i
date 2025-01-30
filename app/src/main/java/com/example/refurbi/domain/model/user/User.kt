package org.refurbi.app.domain.model.user


import kotlinx.serialization.SerialName

@Serializable
data class User(
    @SerialName("address")
    val address: String = "",
    @SerialName("city")
    val city: String = "",
    @SerialName("country")
    val country: String = "",
    @SerialName("email")
    val email: String = "",
    @SerialName("fullName")
    val fullName: String = "",
    @SerialName("id")
    val id: Int = 0,
    @SerialName("password")
    val password: String = "",
    @SerialName("phoneNumber")
    val phoneNumber: String = "",
    @SerialName("postalCode")
    val postalCode: String = "",
    @SerialName("userRole")
    val userRole: String = "",
    @SerialName("username")
    val username: String = "",
    @SerialName("profileImage")
    val profileImage: String = "",
)