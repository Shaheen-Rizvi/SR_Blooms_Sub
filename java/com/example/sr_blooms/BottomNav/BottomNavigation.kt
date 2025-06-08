package com.example.sr_blooms.BottomNav

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Spa
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.sr_blooms.Screen
import com.example.sr_blooms.model.CartViewModel
import com.example.sr_blooms.model.NavItemState

@Composable
fun BottomNavDesign(
    modifier: Modifier = Modifier,
    navController: NavController,
    cartViewModel: CartViewModel
) {
    val items = listOf(
        NavItemState(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home
        ),
        NavItemState(
            title = "Flowers",
            selectedIcon = Icons.Filled.Spa,
            unselectedIcon = Icons.Outlined.Spa
        ),
        NavItemState(
            title = "Wishlist",
            selectedIcon = Icons.Filled.Favorite,
            unselectedIcon = Icons.Outlined.FavoriteBorder
        ),
        NavItemState(
            title = "Profile",
            selectedIcon = Icons.Filled.Person,
            unselectedIcon = Icons.Outlined.Person
        )
    )

    // Use stable state collection
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    val navBarState = when (currentRoute) {
        Screen.Home.route -> 0
        Screen.Search.route -> 1
        Screen.Cart.route -> 2
        Screen.Profile.route -> 3
        else -> -1
    }

    // Use stable cart count
    val cartCount by remember {
        derivedStateOf { cartViewModel.cartSize }
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(70.dp)
            .background(MaterialTheme.colorScheme.tertiary),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        items.forEachIndexed { index, item ->
            Box(
                modifier = Modifier.size(50.dp),
                contentAlignment = Alignment.Center
            ) {
                IconButton(
                    onClick = {
                        if (navBarState != index) {
                            val route = when (index) {
                                0 -> Screen.Home.route
                                1 -> Screen.Search.route
                                2 -> Screen.Cart.route
                                3 -> Screen.Profile.route
                                else -> return@IconButton
                            }

                            // Navigate with proper pop behavior
                            navController.navigate(route) {
                                // Pop up to start destination to avoid deep stack
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }
                ) {
                    Icon(
                        imageVector = if (navBarState == index) item.selectedIcon else item.unselectedIcon,
                        contentDescription = item.title,
                        tint = if (navBarState == index) Color.Black else Color.Gray,
                        modifier = Modifier.size(28.dp)
                    )
                }

                // Cart badge
                if (index == 2 && cartCount > 0) {
                    Box(
                        modifier = Modifier
                            .size(16.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.error)
                            .align(Alignment.TopEnd)
                    ) {
                        Text(
                            text = if (cartCount > 99) "99+" else cartCount.toString(),
                            color = Color.White,
                            fontSize = 10.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
        }
    }
}