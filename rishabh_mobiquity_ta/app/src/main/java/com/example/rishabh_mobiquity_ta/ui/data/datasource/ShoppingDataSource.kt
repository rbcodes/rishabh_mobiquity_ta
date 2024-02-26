package com.example.rishabh_mobiquity_ta.ui.data.datasource

import com.example.rishabh_mobiquity_ta.ui.data.entity.ShoppingResponse
import retrofit2.Response

interface ShoppingDataSource {

    suspend fun getShoppingData(): Response<List<ShoppingResponse>>
}