package com.example.refurbi.data.model

data class User(
    val id: Int,
    val name: String,
    val email: String,
    val password: String,
    val role: String // "admin" or "customer"
)
