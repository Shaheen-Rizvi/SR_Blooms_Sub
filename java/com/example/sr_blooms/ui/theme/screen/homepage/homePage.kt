package com.example.sr_blooms.ui.screen.homepage

import android.app.Application
import android.content.res.Configuration
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.collectAsState

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.sr_blooms.AuthViewModel.AuthState
import com.example.sr_blooms.AuthViewModel.AuthViewModel
import com.example.sr_blooms.Data.FlowerItem
import com.example.sr_blooms.R
import com.example.sr_blooms.common.ScreenWithBottomNavBar
import com.example.sr_blooms.model.CartViewModel
import com.example.sr_blooms.model.Pictures
import com.example.sr_blooms.ui.components.CategoryBar
import com.example.sr_blooms.ui.components.LogoAndCard
import com.example.sr_blooms.ui.components.PromoBanner
import com.example.sr_blooms.ui.theme.Shapes
import com.example.sr_blooms.viewmodel.BatteryStatusViewModel
import kotlinx.coroutines.launch

@Composable
fun homePage(
    navController: NavHostController,
    authViewModel: AuthViewModel,
    cartViewModel: CartViewModel
) {
    var search by remember { mutableStateOf("") }
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    val coroutineScope = rememberCoroutineScope()

    val authState by authViewModel.authState.observeAsState()

    // Navigate to login if unauthenticated
    LaunchedEffect(authState) {
        if (authState is AuthState.unauthenticated) {
            navController.navigate("login") {
                popUpTo("homePage") { inclusive = true }
            }
        }
    }

    // Initialize BatteryStatusViewModel with Application context
    val context = LocalContext.current.applicationContext as Application
    val batteryViewModel: BatteryStatusViewModel = viewModel(
        factory = androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory(context)
    )

    // Observe battery status live data
   // val batteryLevel by batteryViewModel.batteryLevel.observeAsState(100)
    //val isCharging by batteryViewModel.isCharging.observeAsState(false)

    Box(modifier = Modifier.fillMaxSize()) {
        // Content Column
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(bottom = 66.dp) // For bottom nav bar spacing
                .padding(
                    horizontal = if (isLandscape) 50.dp else 2.dp,
                    vertical = if (isLandscape) 15.dp else 10.dp
                )
                .padding(top = if (isLandscape) 2.dp else 5.dp)
                .padding(bottom = if (isLandscape) 2.dp else 20.dp)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(6.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                // Top Bar
                item {
                    Spacer(modifier = Modifier.windowInsetsTopHeight(WindowInsets.statusBars))
                    LogoAndCard()
                }

                // Search Bar
                item {
                    Searchbar(search = search, onSearchChange = { search = it })
                }

                // Category Chips
                item {
                    Spacer(modifier = Modifier.height(10.dp))
                    CategoryBar()
                }

                // Popular Section Header
                item {
                    Spacer(modifier = Modifier.height(5.dp))
                    SectionHeader(
                        title = stringResource(R.string.popular),
                        link = "menuPage",
                        navController = navController
                    )
                }

                // Popular Flower List
                item {
                    Spacer(modifier = Modifier.height(5.dp))
                    PopularFlowerList(
                        pictureList = FlowerItem().loadPopularFlowers(),
                        navController = navController
                    )
                }

                // Exclusive Promotions Section Header
                item {
                    Spacer(modifier = Modifier.height(5.dp))
                    SectionHeader(
                        title = stringResource(R.string.exclusive_promotions),
                        link = "menuPage",
                        navController = navController
                    )
                }

                // Promo Banner (uncomment to enable)
                item {
                    Spacer(modifier = Modifier.height(5.dp))
                    // PromoBanner()
                }

                // Upcoming Events Section Header
                item {
                    Spacer(modifier = Modifier.height(5.dp))
                    SectionHeader(
                        title = stringResource(R.string.future_deals),
                        link = "menuPage",
                        navController = navController
                    )
                }

                // Slideshow (uncomment to enable)
                item {
                    Spacer(modifier = Modifier.height(5.dp))
                    // Slideshow()
                }

                // Battery Status Display (optional at the bottom)
               // item {
                 //   Spacer(modifier = Modifier.height(10.dp))
                   // BatteryStatusDisplay(level = batteryLevel, charging = isCharging)
                }
            }
        }

        // Bottom Navigation Bar
        ScreenWithBottomNavBar(navController = navController, cartViewModel = cartViewModel)
    }


// Section Header composable
@Composable
fun SectionHeader(title: String, link: String, navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 9.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge.copy(
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.SemiBold
            )
        )
        Spacer(modifier = Modifier.weight(1f))
        TextButton(
            onClick = { navController.navigate(link) },
            modifier = Modifier.align(Alignment.CenterVertically)
        ) {
            Text(
                text = stringResource(R.string.show_more),
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.inverseSurface,
                    fontWeight = FontWeight.Bold
                ),
            )
        }
    }
}

// Popular Flower List using LazyRow
@Composable
fun PopularFlowerList(pictureList: List<Pictures>, navController: NavController) {
    LazyRow {
        items(pictureList) { picture ->
            PopularFlowerCard(picture = picture, modifier = Modifier.padding(0.dp), navController = navController)
        }
    }
}

// Popular Flower Card
@Composable
fun PopularFlowerCard(picture: Pictures, modifier: Modifier = Modifier, navController: NavController) {
    Card(
        modifier = modifier
            .padding(horizontal = 7.dp)
            .width(170.dp)
            .shadow(
                elevation = 12.dp,
                shape = Shapes.medium,
                ambientColor = Color.Black,
                spotColor = Color.Black
            )
            .height(240.dp)
            .clickable {
                // Navigate to detailed product view, passing parameters
                navController.navigate("detailedProductView/${picture.imageResourceId}/${picture.imageResourceId2}/${picture.stringResourceId}/${picture.stringResourceId2}/${picture.price}")
            },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column {
            Image(
                painter = painterResource(picture.imageResourceId),
                contentDescription = stringResource(id = R.string.app_name),
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .height(120.dp)
                    .width(190.dp)
            )
            Text(
                text = stringResource(id = picture.stringResourceId),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(2.dp)
            )
            Text(
                text = "Rs ${stringResource(id = picture.price)}",
                color = Color.Green,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(7.dp)
            )
            Button(
                onClick = {
                    navController.navigate("detailedProductView/${picture.imageResourceId}/${picture.stringResourceId}/${picture.price}")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFB2BE))
            ) {
                Text(text = "Buy", style = MaterialTheme.typography.titleMedium, color = Color.Black)
            }
        }
    }
}

// Search bar forwarding composable
@Composable
fun Searchbar(search: String, onSearchChange: (String) -> Unit) {
    com.example.sr_blooms.ui.components.SearchBar(search = search, onSearchChange = onSearchChange)
}

// Battery status display composable
@Composable
fun BatteryStatusDisplay(level: Int, charging: Boolean) {
    Text(
        text = if (charging) "Battery: $level% (Charging 🔌)" else "Battery: $level%",
        style = MaterialTheme.typography.bodyMedium.copy(
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp
        ),
        modifier = Modifier.padding(horizontal = 12.dp)
    )
}

////Slideshow Composable
//@Composable
//fun Slideshow(slideInterval: Long = 3000) {
//    val configuration = LocalConfiguration.current
//    val isPortrait = configuration.orientation == Configuration.ORIENTATION_PORTRAIT
//    val imageHeight = if (isPortrait) 200.dp else 350.dp
//
//    val imageResources = listOf(
//        R.drawable.slide3,
//        R.drawable.slide2,
//        R.drawable.slide1
//    )
//
//    var currentIndex by remember { mutableStateOf(0) }
//
//    // Update the current index after every slideInterval duration
//    LaunchedEffect(currentIndex) {
//        while (true) {
//            delay(slideInterval)
//            currentIndex = (currentIndex + 1) % imageResources.size
//        }
//    }
//
//    // Crossfade for smooth transitions
//    Crossfade(targetState = currentIndex, modifier = Modifier.fillMaxWidth()) { index ->
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(imageHeight)
//                .padding(horizontal = 8.dp)
//                .clip(Shapes.medium) // Added border radius
//        ) {
//            Image(
//                painter = painterResource(id = imageResources[index]),
//                contentDescription = null,
//                contentScale = ContentScale.Crop,
//                modifier = Modifier.fillMaxSize()
//            )
//        }
//    }
//}
