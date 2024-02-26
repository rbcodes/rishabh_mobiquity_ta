package com.example.rishabh_mobiquity_ta.ui.data.datasource

import com.example.rishabh_mobiquity_ta.ui.data.api.ApiService
import com.example.rishabh_mobiquity_ta.ui.data.entity.ShoppingResponse
import retrofit2.Response
import javax.inject.Inject

class ShoppingDataSourceImpl @Inject constructor(
   private val apiService: ApiService
): ShoppingDataSource {

    override suspend fun getShoppingData(): Response<List<ShoppingResponse>> {
       return apiService.getShoppingData()
    }
}