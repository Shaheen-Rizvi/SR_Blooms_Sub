package com.example.sr_blooms.ui.screen.SearchScreen

import android.util.Log
import android.util.Log.e
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.sr_blooms.CommonSections.SectionsText
import com.example.sr_blooms.Data.FlowerData
import com.example.sr_blooms.R
import com.example.sr_blooms.common.ScreenWithBottomNavBar
import com.example.sr_blooms.common.TopBarSection
import com.example.sr_blooms.model.CartViewModel
import com.example.sr_blooms.model.Pictures
import com.example.sr_blooms.ui.screen.homepage.Searchbar

@Composable
fun SearchScreen(navController: NavHostController, cartViewModel: CartViewModel) {
    var search by remember { mutableStateOf("") }

    // Debug: Log when SearchScreen is composed
    LaunchedEffect(Unit) {
        Log.d("SearchScreen", "SearchScreen composing...")
    }

    // Initialize data and check if it's loaded
    val flowerData = remember { FlowerData() }
    val bouquets = remember { flowerData.loadBouquets() }
    val indoorPlants = remember { flowerData.loadIndoorPlants() }
    val giftHampers = remember { flowerData.loadGiftHampers() }

    // Debug logging
    LaunchedEffect(Unit) {
        Log.d("SearchScreen", "Bouquets count: ${bouquets.size}")
        Log.d("SearchScreen", "Indoor plants count: ${indoorPlants.size}")
        Log.d("SearchScreen", "Gift hampers count: ${giftHampers.size}")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentPadding = PaddingValues(10.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                Log.d("SearchScreen", "Rendering TopBarSection")
                TopBarSection(navController = navController, cartViewModel = cartViewModel)
            }

            item {
                Log.d("SearchScreen", "Rendering Searchbar")
                Searchbar(search = search, onSearchChange = { search = it })
            }

            // Debug item to show we're rendering
            item {
                Text(
                    text = "Debug: SearchScreen is rendering",
                    color = Color.Red,
                    modifier = Modifier.padding(8.dp)
                )
            }

            // Flower Category: Bouquets
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                Log.d("SearchScreen", "Rendering SectionsText for bouquets")
                SectionsText(title = stringResource(R.string.popular_bouquets), navController = navController)
            }

            // Debug text for bouquets
            item {
                Text(
                    text = "Debug: Bouquets count = ${bouquets.size}",
                    color = Color.Blue,
                    modifier = Modifier.padding(8.dp)
                )
            }

            if (bouquets.isNotEmpty()) {
                item {
                    Log.d("SearchScreen", "Rendering FlowerList with ${bouquets.size} items")
                    FlowerList(
                        flowerList = bouquets,
                        navController = navController,
                        showAll = true
                    )
                }
            } else {
                item {
                    Text(
                        text = "No bouquets found",
                        color = Color.Red,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }

            // Flower Category: Indoor Plants
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                SectionsText(title = stringResource(R.string.indoor_plants), navController = navController)
            }

            // Debug text for indoor plants
            item {
                Text(
                    text = "Debug: Indoor plants count = ${indoorPlants.size}",
                    color = Color.Blue,
                    modifier = Modifier.padding(8.dp)
                )
            }

            if (indoorPlants.isNotEmpty()) {
                item {
                    IndoorPlantList(
                        plantList = indoorPlants,
                        navController = navController,
                        showAll = true
                    )
                }
            } else {
                item {
                    Text(
                        text = "No indoor plants found",
                        color = Color.Red,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }

            // Flower Category: Gift Hampers
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                SectionsText(title = stringResource(R.string.gift_hampers), navController = navController)
            }

            // Debug text for gift hampers
            item {
                Text(
                    text = "Debug: Gift hampers count = ${giftHampers.size}",
                    color = Color.Blue,
                    modifier = Modifier.padding(8.dp)
                )
            }

            if (giftHampers.isNotEmpty()) {
                item {
                    GiftHamperList(
                        giftList = giftHampers,
                        navController = navController,
                        showAll = true
                    )
                }
            } else {
                item {
                    Text(
                        text = "No gift hampers found",
                        color = Color.Red,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }

            // Bottom padding for bottom navigation
            item {
                Spacer(modifier = Modifier.height(80.dp))
            }
        }

        // Bottom navigation bar
        ScreenWithBottomNavBar(navController = navController, cartViewModel = cartViewModel)
    }
}

@Composable
fun FlowerCard(flower: Pictures, modifier: Modifier, navController: NavController) {
        com.example.sr_blooms.CommonSections.FlowerCard(
            imageResourceId = flower.imageResourceId,
            title = stringResource(id = flower.stringResourceId),
            price = stringResource(id = flower.price),
            onClick = {
                navController.navigate(
                    "detailedProductView/${flower.imageResourceId}/${flower.imageResourceId2}/${flower.stringResourceId}/${flower.stringResourceId2}/${flower.price}"
                )
            }
        )
        // Fallback UI
        Text(
            text = "Error loading flower card",
            color = Color.Red,
            modifier = modifier.padding(8.dp)
        )
    }


@Composable
fun FlowerList(flowerList: List<Pictures>, navController: NavController, showAll: Boolean = false) {
    Log.d("SearchScreen", "FlowerList rendering with ${flowerList.size} items")

    val listToShow = if (showAll) flowerList else flowerList.take(5)

    if (listToShow.isEmpty()) {
        Text(
            text = "No flowers to display",
            color = Color.Gray,
            modifier = Modifier.padding(16.dp)
        )
        return
    }

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 4.dp)
    ) {
        items(listToShow) { flower ->
            FlowerCard(
                flower = flower,
                modifier = Modifier.padding(4.dp),
                navController = navController
            )
        }
    }
}

@Composable
fun IndoorPlantCard(plant: Pictures, modifier: Modifier, navController: NavController) {
    FlowerCard(flower = plant, modifier = modifier, navController = navController)
}

@Composable
fun IndoorPlantList(plantList: List<Pictures>, navController: NavController, showAll: Boolean = false) {
    Log.d("SearchScreen", "IndoorPlantList rendering with ${plantList.size} items")
    FlowerList(flowerList = plantList, navController = navController, showAll = showAll)
}

@Composable
fun GiftHamperCard(gift: Pictures, modifier: Modifier, navController: NavController) {
    FlowerCard(flower = gift, modifier = modifier, navController = navController)
}

@Composable
fun GiftHamperList(giftList: List<Pictures>, navController: NavController, showAll: Boolean = false) {
    Log.d("SearchScreen", "GiftHamperList rendering with ${giftList.size} items")
    FlowerList(flowerList = giftList, navController = navController, showAll = showAll)
}