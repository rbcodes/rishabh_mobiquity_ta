package com.example.rishabh_mobiquity_ta.ui.repository

import com.example.rishabh_mobiquity_ta.common.ApiState
import com.example.rishabh_mobiquity_ta.ui.data.entity.Products
import com.example.rishabh_mobiquity_ta.ui.data.entity.SalePrice
import com.example.rishabh_mobiquity_ta.ui.data.entity.ShoppingResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ShoppingRepositoryTest : DefaultShoppingRepository{


    private var shouldReturnNetworkError = false

    fun SetShouldReturnNetworkError(value: Boolean){
        shouldReturnNetworkError = value
    }

    override suspend fun getShoppingData() : Flow<ApiState<List<ShoppingResponse>>> {
            if(shouldReturnNetworkError){
                return flow {
                        emit(ApiState.Error("Error Fetching Api Data"))
                    }
            } else {
                return flow {
                    val shoppingList = listOf<ShoppingResponse>(
                        ShoppingResponse(
                            "36802", "Food", "",
                            listOf(
                                Products(
                                    "1", "36802", "Bread", "/Bread.jpg", "",
                                    SalePrice("0.81", "EUR")
                                )
                            )
                        )
                    )
                    emit(ApiState.Success(shoppingList))
                }
            }
    }
}
