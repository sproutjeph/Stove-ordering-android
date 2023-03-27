package com.stovepos.stoveorderingandroidapp.data.remote.menu_data_dto

data class VenuePromo(
    val availableKiosk: Int,
    val availableOnline: Int,
    val availableOnsite: Int,
    val background_color: Any,
    val buttonText: String,
    val catid: Any,
    val displayButton: String,
    val includeTax: Int,
    val itemid: Any,
    val minamount: Any,
    val promo_active: String,
    val promo_code: String,
    val promo_created: Any,
    val promo_end_date: Any,
    val promo_name: String,
    val promo_start_date: Any,
    val promo_type: Int,
    val promo_use_per_user: Any,
    val promo_value: String,
    val receiptText: String,
    val recid: Int,
    val restrictions: Any,
    val usefirst: Any,
    val useonce: Any,
    val venueid: String
)