package com.example.rishabh_mobiquity_ta.ui.repository

import com.example.rishabh_mobiquity_ta.common.ApiState
import com.example.rishabh_mobiquity_ta.ui.data.datasource.ShoppingDataSource
import com.example.rishabh_mobiquity_ta.ui.data.entity.ShoppingResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ShoppingRepository @Inject constructor(
    private val shoppingDataSource: ShoppingDataSource
): DefaultShoppingRepository{


    override suspend fun getShoppingData() : Flow<ApiState<List<ShoppingResponse>>> {
        return flow {
            emit(ApiState.Loading())

            val response = shoppingDataSource.getShoppingData()

            if(response.isSuccessful && response.body() != null){
                emit(ApiState.Success(response.body()!!))
            }
            else{
                emit(ApiState.Error("Error Fetching Api Data"))
            }
        }.catch { e->
            emit(ApiState.Error("Some error in flow ${e?.localizedMessage}"))
        }
    }

}