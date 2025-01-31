package com.example.refurbi.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.refurbi.data.model.User

class AuthViewModel : ViewModel() {

    // Dummy users for demonstration:
    private val dummyUsers = listOf(
        User(id = 1, name = "Admin", email = "admin@example.com", password = "admin", role = "admin"),
        User(id = 2, name = "User", email = "user@example.com", password = "user", role = "customer")
    )

    private val _currentUser = mutableStateOf<User?>(null)
    val currentUser = _currentUser

    /**
     * Attempt to log in. Returns true if successful, false otherwise.
     */
    fun login(email: String, password: String): Boolean {
        val user = dummyUsers.find { it.email == email && it.password == password }
        return if (user != null) {
            _currentUser.value = user
            true
        } else {
            false
        }
    }

    fun logout() {
        _currentUser.value = null
    }
}
