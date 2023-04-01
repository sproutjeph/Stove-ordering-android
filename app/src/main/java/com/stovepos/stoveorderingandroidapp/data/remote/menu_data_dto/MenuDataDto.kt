package com.stovepos.stoveorderingandroidapp.data.remote.menu_data_dto

data class MenuDataDto(
    val menuAltMods: List<MenuAltMod>,
    val menuCat: List<MenuCat>,
    val menuClass: List<Any>,
    val menuItems: List<MenuItem>,
    val message: String,
    val order_types: List<OrderType>,
    val result: Int,
    val sources: List<Source>,
    val table_section: List<TableSection>,
    val venuePromo: List<VenuePromo>
)