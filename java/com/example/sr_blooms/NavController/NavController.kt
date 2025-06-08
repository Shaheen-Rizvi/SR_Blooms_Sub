package com.example.sr_blooms.NavController


import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.example.sr_blooms.AuthViewModel.AuthViewModel
import com.example.sr_blooms.Screen
import com.example.sr_blooms.model.CartViewModel
import com.example.sr_blooms.model.UserProfileViewModel
import com.example.sr_blooms.ui.screen.*
import com.example.sr_blooms.ui.screen.CartScreen.CartScreen
import com.example.sr_blooms.ui.screen.DetailedProductView.DetailedProductView
import com.example.sr_blooms.ui.screen.MenuScreen.MenuPage
import com.example.sr_blooms.ui.screen.OrderConfirmation.OrderConfirmation
import com.example.sr_blooms.ui.screen.ProfileScreen.ProfileScreen
import com.example.sr_blooms.ui.screen.SearchScreen.SearchScreen
import com.example.sr_blooms.ui.screen.homepage.homePage
import com.example.sr_blooms.ui.screen.login.LoginScreen
import com.example.sr_blooms.ui.screen.signup.signupPage

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavigation(userProfileViewModel: UserProfileViewModel, cartViewModel: CartViewModel, authViewModel: AuthViewModel,navController: NavHostController ) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {



        // Fade effect for smooth transition.
        AnimatedNavHost(
            navController = navController,
            startDestination = Screen.Login.route,
            enterTransition = { slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(500)) + fadeIn() },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -it }, animationSpec = tween(500)) + fadeOut() },
            popEnterTransition = { slideInHorizontally(initialOffsetX = { -it }, animationSpec = tween(500)) + fadeIn() },
            popExitTransition = { slideOutHorizontally(targetOffsetX = { it }, animationSpec = tween(500)) + fadeOut() }
        ) {
            composable(Screen.Home.route) {
                homePage(navController = navController, authViewModel,cartViewModel)
            }
            composable(Screen.Profile.route) {
                ProfileScreen(navController = navController, userProfileViewModel, authViewModel, cartViewModel)
            }
            composable(Screen.Search.route) {
                SearchScreen(
                    navController = navController,
                    cartViewModel = cartViewModel
                )
            }
            composable(Screen.Cart.route) {
                CartScreen(navController = navController, cartViewModel)
            }
            composable(Screen.Login.route) {
                LoginScreen(navController = navController, authViewModel)
            }
            composable(Screen.Signup.route) {
                signupPage(navController = navController, authViewModel)
            }
            composable(Screen.Menu.route) {
                MenuPage(navController = navController)
            }
            composable(Screen.OrderConfirmation.route) {
                OrderConfirmation(navController = navController, cartViewModel)
            }

            //passing arguments to the detailed view page
            composable(
                route = "detailedProductView/{imageResId}/{imageResId2}/{nameResId}/{descResId}/{priceResId}",
                arguments = listOf(
                    navArgument("imageResId") { type = NavType.IntType },
                    navArgument("imageResId2") { type = NavType.IntType },
                    navArgument("nameResId") { type = NavType.IntType },
                    navArgument("descResId") { type = NavType.IntType },
                    navArgument("priceResId") { type = NavType.IntType }
                )
            ) { backStackEntry ->
                // Extract arguments from the navigation route
                val imageResId = backStackEntry.arguments?.getInt("imageResId") ?: 0
                val imageResId2 = backStackEntry.arguments?.getInt("imageResId2") ?: 0
                val nameResId = backStackEntry.arguments?.getInt("nameResId") ?: 0
                val descResId = backStackEntry.arguments?.getInt("descResId") ?: 0
                val priceResId = backStackEntry.arguments?.getInt("priceResId") ?: 0

                // Navigate to the DetailedProductView and pass the extracted arguments
                DetailedProductView(
                    navController = navController,
                    cartViewModel = cartViewModel,
                    imageResId = imageResId,
                    imageResId2 = imageResId2,
                    nameResId = nameResId,
                    descResId = descResId,
                    priceResId = priceResId
                )
            }
        }
    }
}
