package com.example.rishabh_mobiquity_ta.ui.data.entity

class ShoppingResponse (
    val id: String,
    val name: String,
    val description: String,
    val products: List<Products>
        )

class Products(
    val id: String,
    val categoryId: String,
    val name: String,
    val url: String,
    val description: String,
    val salePrice: SalePrice
)

class SalePrice(
    val amount: String,
    val currency: String,
)