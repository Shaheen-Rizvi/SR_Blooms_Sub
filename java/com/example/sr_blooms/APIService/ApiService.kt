package com.example.sr_blooms.APIService

import com.example.sr_blooms.model.CategoryResponse
import com.example.sr_blooms.model.PlantsResponse
import com.example.sr_blooms.model.GiftResponse
import com.example.sr_blooms.model.BouquetsResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

// ApiService.kt
interface ApiService {
    @GET("categories.json")
    suspend fun getCategories(): CategoryResponse

    @GET("plants.json")
    suspend fun getPlants(): PlantsResponse

    @GET("gifts.json")
    suspend fun getGifts(): GiftResponse

    @GET("bouquets.json")
    suspend fun getBouquet(): BouquetsResponse
}

// NetworkClient.kt
object NetworkClient {
    private const val BASE_URL = "https://raw.githubusercontent.com/Shaheen-Rizvi/SR_Blooms_Server/main/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)
}
