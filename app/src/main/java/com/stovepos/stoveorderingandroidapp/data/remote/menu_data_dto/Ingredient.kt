package com.stovepos.stoveorderingandroidapp.data.remote.menu_data_dto

data class Ingredient(
    val name: String,
    val qty: String,
    val sku: String,
    val sortOrder: Int,
    val unit: String
)