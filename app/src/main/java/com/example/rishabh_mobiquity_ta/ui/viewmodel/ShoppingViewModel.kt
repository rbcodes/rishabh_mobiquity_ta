package com.example.rishabh_mobiquity_ta.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rishabh_mobiquity_ta.common.ApiState
import com.example.rishabh_mobiquity_ta.ui.data.entity.Products
import com.example.rishabh_mobiquity_ta.ui.data.entity.ShoppingResponse
import com.example.rishabh_mobiquity_ta.ui.repository.DefaultShoppingRepository
import com.example.rishabh_mobiquity_ta.ui.repository.ShoppingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingViewModel @Inject constructor(
    private val shoppingRepository: DefaultShoppingRepository
): ViewModel(){

    private val _shoppingData : MutableStateFlow<ApiState<List<ShoppingResponse>>> = MutableStateFlow(ApiState.Loading())
    val shoppingData : StateFlow<ApiState<List<ShoppingResponse>>> = _shoppingData

    init {
        getShoppingData()
    }

    private fun getShoppingData(){
        viewModelScope.launch(Dispatchers.IO) {
            shoppingRepository.getShoppingData()
                .collectLatest { shoppingResponse ->
                    _shoppingData.value = shoppingResponse
                }
        }
    }

    companion object {
        const val TAG = "ShoppingApplication "
    }
}