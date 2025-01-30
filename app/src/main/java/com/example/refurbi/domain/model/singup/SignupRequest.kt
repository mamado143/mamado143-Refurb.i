package org.refurbi.app.domain.model.singup

@Serializable
data class SignupRequest(
    val username: String,
    val email: String,
    val password: String,
    val fullName: String? =null,
    val address: String? = null,
    val city: String? = null,
    val country: String? = null,
    val phoneNumber: String?=null,
    val userRole: String
)
