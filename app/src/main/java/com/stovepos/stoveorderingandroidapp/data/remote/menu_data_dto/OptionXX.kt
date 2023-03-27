package com.stovepos.stoveorderingandroidapp.data.remote.menu_data_dto

data class OptionXX(
    val description: String,
    val displayOrder: Int,
    val id: String,
    val name: String,
    val price: Int,
    val priceCurrency: String,
    val sku: String,
    val isSelected:Boolean = false
)