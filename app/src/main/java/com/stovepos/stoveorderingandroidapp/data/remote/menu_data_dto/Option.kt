package com.stovepos.stoveorderingandroidapp.data.remote.menu_data_dto

data class Option(
    val description: String?= null,
    val displayOrder: Int? = null,
    val eligibleQuantityMax: Int? = null,
    val id: String? = null,
    val name: String? = null,
    val optionType: Int? = null,
    val options: List<OptionX>? = null,
    val price: Double? = null,
    val priceCurrency: String? = null,
    val required: Int? = null,
    val sku: String? = null,
    var isSelected: Boolean = false
)