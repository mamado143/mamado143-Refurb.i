package com.example.refurbi.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Login : Screen("login")
    object ProductCatalog : Screen("product_catalog")
    object ProductDetails : Screen("product_details")
    object Cart : Screen("cart")
    object Checkout : Screen("checkout")
    object AdminPanel : Screen("admin_panel")
    object Profile : Screen("profile")

}
