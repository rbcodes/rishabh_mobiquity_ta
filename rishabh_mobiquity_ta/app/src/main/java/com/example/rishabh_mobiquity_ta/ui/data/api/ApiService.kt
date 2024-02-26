package com.example.rishabh_mobiquity_ta.ui.data.api

import com.example.rishabh_mobiquity_ta.ui.data.entity.ShoppingResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET(".")
    suspend fun getShoppingData() : Response<List<ShoppingResponse>>

}

//http://mobcategories.s3-website-eu-west-1.amazonaws.com/