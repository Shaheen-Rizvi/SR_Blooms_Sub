package com.example.sr_blooms.Data

import androidx.annotation.DrawableRes
//import com.example.sr_blooms.APIService.NetworkClient
import com.example.sr_blooms.R
import com.example.sr_blooms.model.CategoryModel
import com.example.sr_blooms.model.CategoryPictures
import com.example.sr_blooms.model.Pictures
import java.io.IOException

class FlowerItem {
    fun loadPopularFlowers(): List<Pictures> {
        return listOf(
            Pictures(
                R.drawable.rose_bouquet,
                R.drawable.rose_bouquet,
                R.string.redRose,
                R.string.redRoseDesc,
                R.string.redRosePrice,
            ),
            Pictures(
                R.drawable.white_rose_bouquet,  // You must have this drawable too
                R.drawable.white_rose_bouquet,
                R.string.whiteRose,
                R.string.whiteRoseDesc,
                R.string.whiteRosePrice,
            ),
            Pictures(
                R.drawable.pink_rose_bouquet,   // You must have this drawable too
                R.drawable.pink_rose_bouquet,
                R.string.pinkRose,
                R.string.pinkRoseDesc,
                R.string.pinkRosePrice,
            ),
            Pictures(
                R.drawable.lily_arrangement ,  // You must have this drawable too
                R.drawable.lily_arrangement,
                R.string.callaLily,
                R.string.callaLilyDesc,
                R.string.callaLilyPrice,
            ),
        )
    }


    class BouquetItems {
    fun loadBouquets(): List<Pictures> {
        return listOf(
            Pictures(
                R.drawable.valentines_bouquet,
                R.drawable.fresh,
                R.string.ValentinesBouquet,
                R.string.valentinesBouquetDesc,
                R.string.ValentinesBouquetPrice,
            ),
            Pictures(
                R.drawable.wedding_bouquet,
                R.drawable.fresh,
                R.string.WeddingBouquet,
                R.string.weddingBouquetDesc,
                R.string.WeddingBouquetPrice,
            ),
            Pictures(
                R.drawable.birthday_bouquet,
                R.drawable.fresh,
                R.string.BirthdayBouquet,
                R.string.birthdayBouquetDesc,
                R.string.BirthdayBouquetPrice,
            ),
            Pictures(
                R.drawable.anniversary_bouquet,
                R.drawable.fresh,
                R.string.AnniversaryBouquet,
                R.string.anniversaryBouquetDesc,
                R.string.AnniversaryBouquetPrice
            ),
        )
    }
}

class GiftPlantsData {
    fun loadGiftPlants(): List<Pictures> {
        return listOf(
            Pictures(
                R.drawable.succulent,
                R.drawable.plant,
                R.string.Succulent,
                R.string.succulentDesc,
                R.string.SucculentPrice
            ),
            Pictures(
                R.drawable.cactus,
                R.drawable.plant,
                R.string.Cactus,
                R.string.cactusDesc,
                R.string.CactusPrice
            ),
            Pictures(
                R.drawable.bonsai,
                R.drawable.plant,
                R.string.Bonsai,
                R.string.bonsaiDesc,
                R.string.BonsaiPrice
            ),
            Pictures(
                R.drawable.orchid,
                R.drawable.fresh,
                R.string.Orchid,
                R.string.orchidDesc,
                R.string.OrchidPrice
            )
        )
    }
}

object Category {
    // Local cache for categories
    private var cachedCategories: List<CategoryModel>? = null

    // Local loading (unchanged)
    fun loadCategoryLocal(): List<CategoryPictures> {
        return listOf(
            CategoryPictures(
                R.drawable.rose_bouquet,
                R.string.Bouquets,
            ),
            CategoryPictures(
                R.drawable.succulent,
                R.string.GiftPlants,
            ),
            // Add other categories here
        )
    }
}}
//
//    // Remote loading with offline detection
//    suspend fun loadCategoryRemote(isNetworkAvailable: Boolean): Result<List<CategoryModel>> {
//        return if (!isNetworkAvailable) {
//            cachedCategories?.let { Result.success(it) }
//                ?: Result.failure(Exception("Offline and no cached data available"))
//        } else {
//            try {
//                val response = NetworkClient.apiService.getCategories()
//                cachedCategories = response.categories // Cache the result
//                Result.success(response.categories)
//            } catch (e: Exception) {
//                if (e is IOException || e.message?.contains("network", true) == true) {
//                    cachedCategories?.let { Result.success(it) }
//                        ?: Result.failure(Exception("Offline and no cached data available"))
//                } else {
//                    Result.failure(e)
//                }
//            }
//        }
//    }
//}}