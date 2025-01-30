package org.refurbi.app.domain.model.login

@Serializable
data class LoginRequest(
    val email: String,
    val password: String
)
