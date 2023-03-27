package com.stovepos.stoveorderingandroidapp.data.remote.menu_data_dto

data class OptionX(
    val displayOrder: Int,
    val eligibleQuantityMax: Int,
    val id: String,
    val name: String,
    val optionType: Int,
    val options: List<OptionXX>,
    val required: Int,
    val sku: String
)