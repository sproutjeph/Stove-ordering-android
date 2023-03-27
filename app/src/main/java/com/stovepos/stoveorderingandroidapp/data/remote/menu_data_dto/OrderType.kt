package com.stovepos.stoveorderingandroidapp.data.remote.menu_data_dto

data class OrderType(
    val id: Int,
    val name: String,
    val promptFirst: Boolean,
    val required_customer_name: Boolean,
    val status: Boolean
)