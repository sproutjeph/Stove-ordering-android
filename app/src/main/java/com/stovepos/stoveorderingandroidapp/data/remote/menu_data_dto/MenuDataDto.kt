package com.stovepos.stoveorderingandroidapp.data.remote.menu_data_dto

data class MenuDataDto(
    val menuAltMods: List<com.stovepos.stoveorderingandroidapp.data.remote.menu_data_dto.MenuAltMod>,
    val menuCat: List<com.stovepos.stoveorderingandroidapp.data.remote.menu_data_dto.MenuCat>,
    val menuClass: List<Any>,
    val menuItems: List<com.stovepos.stoveorderingandroidapp.data.remote.menu_data_dto.MenuItem>,
    val message: String,
    val order_types: List<com.stovepos.stoveorderingandroidapp.data.remote.menu_data_dto.OrderType>,
    val result: Int,
    val sources: List<com.stovepos.stoveorderingandroidapp.data.remote.menu_data_dto.Source>,
    val table_section: List<com.stovepos.stoveorderingandroidapp.data.remote.menu_data_dto.TableSection>,
    val venuePromo: List<com.stovepos.stoveorderingandroidapp.data.remote.menu_data_dto.VenuePromo>
)