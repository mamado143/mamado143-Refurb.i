// File: AppBottomBar.kt
package com.example.refurbi.ui.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.refurbi.navigation.Screen

sealed class BottomBarScreen(val route: String, val title: String, val icon: androidx.compose.ui.graphics.vector.ImageVector) {
    object Home : BottomBarScreen(Screen.Home.route, "Home", Icons.Default.Home)
    object Catalog : BottomBarScreen(Screen.ProductCatalog.route, "Catalog", Icons.Default.List)
    object Profile : BottomBarScreen(Screen.Profile.route, "Profile", Icons.Default.Person)
}

@Composable
fun AppBottomBar(navController: NavController) {
    val items = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Catalog,
        BottomBarScreen.Profile
    )
    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { screen ->
            BottomNavigationItem(
                icon = { Icon(screen.icon, contentDescription = screen.title) },
                label = { Text(screen.title) },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        // Avoid building up a large back stack by using these options
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}
