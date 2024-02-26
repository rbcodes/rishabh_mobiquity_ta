package com.example.rishabh_mobiquity_ta.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rishabh_mobiquity_ta.ui.screens.HomeScreen
import com.example.rishabh_mobiquity_ta.ui.screens.MovieDetailScreen

@Composable
fun AppNavigationGraph(){

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination =  Routes.HOME_SCREEN){
        composable(Routes.HOME_SCREEN){
            HomeScreen( navHostController = navController)
        }
        composable(Routes.PRODUCT_DETAILS+"?{image_url}?{product_name}?{product_price}"){
                backStackEntry ->
            MovieDetailScreen(
                image_url = backStackEntry?.arguments?.getString("image_url"),
                product_name = backStackEntry?.arguments?.getString("product_name"),
                product_price = backStackEntry.arguments?.getString("product_price"),
                navHostController = navController)
        }
    }
}