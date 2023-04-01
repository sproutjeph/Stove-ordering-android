package com.stovepos.stoveorderingandroidapp.data.remote.menu_data_dto

data class MenuItem(
    val active: String,
    val availableDays_json: List<Any>,
    val backgroundcolor: String,
    val barcodeid: Any,
    val colorType: String,
    val hungritemid: String,
    val ingredients_json: IngredientsJson,
    val itemImages_json: List<String>? = null,
    val itemOptions_json: List<ItemOptionsJson>?,
    val itemTextColor: String,
    val item_class: String,
    val item_mods: String,
    val item_name: String,
    val item_price: String,
    val itemid: Int,
    val menucatid: Int,
    val mod_prompt: Int,
    val multiPrice_json: List<Any>,
    val printerid: String,
    val styleOptions: Any,
    val taxrate: String,
    val itemQty:Int = 1
)