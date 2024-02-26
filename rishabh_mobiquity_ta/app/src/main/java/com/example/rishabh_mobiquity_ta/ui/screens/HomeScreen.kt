package com.example.rishabh_mobiquity_ta.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.rishabh_mobiquity_ta.common.ApiState
import com.example.rishabh_mobiquity_ta.ui.components.ShoppingList
import com.example.rishabh_mobiquity_ta.ui.data.entity.ShoppingResponse
import com.example.rishabh_mobiquity_ta.ui.viewmodel.ShoppingViewModel

const val TAG = "HomeScreen"
@Composable
fun HomeScreen (
    shoppingViewModel: ShoppingViewModel = hiltViewModel(),
    navHostController: NavHostController
){
    val shoppingResponse by shoppingViewModel.shoppingData.collectAsState()
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        when(shoppingResponse){
            is ApiState.Loading ->{
                Log.d(TAG, "Inside Loading")
            }
            is ApiState.Success ->{
                val response = (shoppingResponse as ApiState.Success).data
                Log.d(TAG, "Inside Success $response")
                ShoppingList(response, navHostController)
            }
            is ApiState.Error ->{
                val error = (shoppingResponse as ApiState.Error)
                Log.d(TAG, "Error on Home Screen $error")
            }
        }
    }

}
