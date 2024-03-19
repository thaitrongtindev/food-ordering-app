package com.example.foodorderingapp.model

data class MenuItem(
    val foodName: String?,
    val foodPrice: String?,
    val foodDescription: String?,
    val foodIngredient: String?,
    val foodImage: String?) {
    constructor() : this(null, null, null, null, null)
}

