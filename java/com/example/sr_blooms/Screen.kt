package com.example.sr_blooms

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String) {
    object Home : Screen("home") // Home screen with featured flowers
    object Search : Screen("search")
  //  object Flowers : Screen("flowers") // List of all available flowers
    object Cart : Screen("wishlist") // Shopping cart screen
    object Profile : Screen("profile") // User profile or settings
    object Login : Screen("login") // Login screen
    object Signup : Screen("signup") // Signup screen
    object FlowerDetails : Screen("flowerDetails") // Detailed flower view
    object OrderConfirmation : Screen("orderConfirmation") // Order success screen
    object Menu : Screen("menuPage")

}


