package com.example.rishabh_mobiquity_ta.ui.repository

import com.example.rishabh_mobiquity_ta.common.ApiState
import com.example.rishabh_mobiquity_ta.ui.data.entity.ShoppingResponse
import kotlinx.coroutines.flow.Flow

interface DefaultShoppingRepository {

    suspend fun getShoppingData() : Flow<ApiState<List<ShoppingResponse>>>
}