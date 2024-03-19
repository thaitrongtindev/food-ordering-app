package com.example.foodorderingapp.model

data class CartItems(
    val foodName: String?,
    val foodPrice: String?,
    val foodDescription: String?,
    val foodImage: String?,
    val foodQuantity: Int?,
    val foodIngredient: String?


) {
    constructor() : this(null, null, null, null, null, null)
}

