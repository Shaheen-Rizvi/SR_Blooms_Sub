package com.example.sr_blooms.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sr_blooms.BottomNav.BottomNavDesign
import com.example.sr_blooms.Screen
import com.example.sr_blooms.TopNav.TopBar
import com.example.sr_blooms.model.CartViewModel


@Composable
fun ScreenWithBottomNavBar(navController: NavController, cartViewModel: CartViewModel) {
    Column(modifier = Modifier.fillMaxSize()) {
        // Main content goes here
        Box(
            modifier = Modifier
                .weight(1f)
                .background(MaterialTheme.colorScheme.background)
        ) {}
        BottomNavSection(navController = navController, cartViewModel = cartViewModel)
    }
}

@Composable
fun BottomNavSection(navController: NavController, cartViewModel: CartViewModel) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation = 8.dp)
            .height(80.dp),
        contentAlignment = Alignment.Center
    ) {
        BottomNavDesign(navController = navController, cartViewModel = cartViewModel)
    }
}

@Composable
fun CartActionBar(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 1.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Back to Flowers Button
        Button(
            onClick = { navController.popBackStack() },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
            shape = RoundedCornerShape(35),
            modifier = Modifier
                .padding(12.dp)
                .height(50.dp)
                .width(170.dp),
            elevation = ButtonDefaults.buttonElevation(2.dp)
        ) {
            Text(
                "Back to Flowers",
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(horizontal = 2.dp)
            )
        }

        // Confirm Order Button
        Button(
            onClick = { navController.navigate(Screen.OrderConfirmation.route) },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
            shape = RoundedCornerShape(35),
            modifier = Modifier
                .padding(12.dp)
                .height(50.dp)
                .width(160.dp),
            elevation = ButtonDefaults.buttonElevation(2.dp)
        ) {
            Text(
                "Confirm Order",
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(horizontal = 2.dp)
            )
        }
    }

    Spacer(modifier = Modifier.height(26.dp))
}

@Composable
fun TopBarSection(navController: NavController, cartViewModel: CartViewModel) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent),
        contentAlignment = Alignment.Center
    ) {
        TopBar(navController = navController, cartViewModel = cartViewModel)
    }
}
