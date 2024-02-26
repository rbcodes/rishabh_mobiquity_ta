package com.example.rishabh_mobiquity_ta.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.rishabh_mobiquity_ta.ui.components.ProductComponents

@Composable
fun MovieDetailScreen (
    image_url: String?,
    product_name: String?,
    product_price: String?,
    navHostController: NavHostController
){
    ProductComponents(
        image_url = image_url,
        product_name = product_name,
        product_price = product_price,
        navHostController = navHostController)
}
