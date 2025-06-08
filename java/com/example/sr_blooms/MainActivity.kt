package com.example.sr_blooms

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.sr_blooms.AuthViewModel.AuthViewModel
import com.example.sr_blooms.NavController.AppNavigation
import com.example.sr_blooms.model.CartViewModel
import com.example.sr_blooms.model.UserProfileViewModel
import com.example.sr_blooms.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                val userProfileViewModel: UserProfileViewModel = viewModel()
                val cartViewModel: CartViewModel = viewModel()
                val authViewModel: AuthViewModel = viewModel()
                val navController = rememberNavController()

                AppNavigation(
                    userProfileViewModel = userProfileViewModel,
                    cartViewModel = cartViewModel,
                    authViewModel = authViewModel,
                    navController = navController

                )
            }
        }
    }
}
