package com.example.refurbi.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.refurbi.ui.screens.*

@Composable
fun NavGraph(startDestination: String) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {

        // 🚀 Splash Screen (Handles Initial Navigation)
        composable("splash") {
            SplashScreen(onSplashFinished = {
                navController.navigate("login") {
                    popUpTo("splash") { inclusive = true }
                }
            })
        }

        // 📌 Authentication & User Screens
        composable("login") { LoginScreen(navController) }
        composable("home") { HomeScreen(navController) }
        composable("profile") { ProfileScreen(navController) }
        composable("order_history") { OrderHistoryScreen(navController) }

        // 📦 Product Management
        composable("product_catalog") { ProductCatalogScreen(navController) }
        composable(
            "product_details/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId") ?: 0
            ProductDetailsScreen(productId, navController)
        }

        // 🛒 Shopping Flow
        composable("cart") { CartScreen(navController) }
        composable("checkout") { CheckoutScreen(navController) }
        composable("order_confirmation") { OrderConfirmationScreen(navController) }

        // 🔧 Admin Features
        composable("admin_panel") { AdminPanelScreen(navController) }
        composable(
            "add_edit_product?productId={productId}",
            arguments = listOf(navArgument("productId") { type = NavType.IntType; defaultValue = -1 })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId") ?: -1
            AddEditProductScreen(navController, productId)
        }
        composable("analytics") { AnalyticsScreen(navController) }

        // 🏷️ Categories & Filtering
        composable("categories") { CategoryScreen(navController) }
        composable(
            "category_products/{categoryId}",
            arguments = listOf(navArgument("categoryId") { type = NavType.IntType })
        ) { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getInt("categoryId") ?: 0
            CategoryProductsScreen(navController, categoryId)
        }

        // 💳 Payment & Reviews
        composable(
            "payment/{totalPrice}",
            arguments = listOf(navArgument("totalPrice") { type = NavType.StringType })
        ) { backStackEntry ->
            val totalPrice = backStackEntry.arguments?.getString("totalPrice")?.toDoubleOrNull() ?: 0.0
            PaymentScreen(navController, totalPrice, onPaymentSuccess = {
                navController.navigate("order_confirmation")
            }, onPaymentFailure = { message ->
                // Handle payment error (show Snackbar, etc.)
            })
        }

        composable(
            "review/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId") ?: 0
            ReviewScreen(navController, productId, onSubmitReview = { rating, comment ->
                navController.popBackStack()
            })
        }
    }
}
