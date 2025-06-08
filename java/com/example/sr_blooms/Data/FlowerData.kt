package com.example.sr_blooms.Data


import com.example.sr_blooms.R
import com.example.sr_blooms.model.Pictures

class FlowerData {
    fun loadBouquets(): List<Pictures> {
        return listOf(
            Pictures(R.drawable.bouquet1, R.drawable.bouquet1_alt, R.string.rose_bouquet, R.string.fresh_roses, R.string.price_rose),
            Pictures(R.drawable.bouquet2, R.drawable.bouquet2_alt, R.string.sunflower_bouquet, R.string.bright_sunflowers, R.string.price_sunflower)
            // Add more...
        )
    }

    fun loadIndoorPlants(): List<Pictures> {
        return listOf(
            Pictures(R.drawable.plant1, R.drawable.plant1_alt, R.string.peace_lily, R.string.peace_lily_desc, R.string.price_peace_lily),
            Pictures(R.drawable.plant2, R.drawable.plant2_alt, R.string.snake_plant, R.string.snake_plant_desc, R.string.price_snake_plant)
        )
    }

    fun loadGiftHampers(): List<Pictures> {
        return listOf(
            Pictures(R.drawable.gift1, R.drawable.gift1_alt, R.string.romantic_hamper, R.string.romantic_desc, R.string.price_romantic),
            Pictures(R.drawable.gift2, R.drawable.gift2_alt, R.string.spa_hamper, R.string.spa_desc, R.string.price_spa)
        )
    }
}
