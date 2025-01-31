package com.example.refurbi.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.refurbi.ui.screens.*

@Composable
fun NavGraph(startDestination: String = Screen.Home.route) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        composable(Screen.Home.route) { HomeScreen(navController) }
        composable(Screen.Login.route) { LoginScreen(navController) }
        composable(Screen.ProductCatalog.route) { ProductCatalogScreen(navController) }
        composable(
            route = "${Screen.ProductDetails.route}/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId") ?: 0
            ProductDetailsScreen(productId, navController)
        }
        composable(Screen.Cart.route) { CartScreen(navController) }
        composable(Screen.Checkout.route) { CheckoutScreen(navController) }
        composable(Screen.AdminPanel.route) { AdminPanelScreen(navController) }
        // New route for Add/Edit Product Screen with an optional productId parameter.
        composable(
            route = "add_edit_product?productId={productId}",
            arguments = listOf(
                navArgument("productId") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId") ?: -1
            AddEditProductScreen(navController = navController, productId = productId)
        }
    }
}
