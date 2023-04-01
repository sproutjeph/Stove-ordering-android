package com.stovepos.stoveorderingandroidapp.presentation.screens.home

import com.stovepos.stoveorderingandroidapp.data.local.MenuCatButtonModel
import com.stovepos.stoveorderingandroidapp.data.local.MenuItemModel
import com.stovepos.stoveorderingandroidapp.data.local.VenueInfoModel
import com.stovepos.stoveorderingandroidapp.data.remote.menu_data_dto.MenuDataDto
import com.stovepos.stoveorderingandroidapp.data.remote.venue_info_dto.VenueInfoDto

data class MenuDataState(
    val isLoading: Boolean = false,
    val menuData: MenuData? = null,
    val error: String = ""
)

data class MenuData(
    val menuItems: List<MenuItemModel>,
    val venueInfo: VenueInfoModel? = null,
    val menuCatButtons: List<MenuCatButtonModel>
)


data class VenueInfoState(
    val isLoading: Boolean = false,
    val venueInfo: VenueInfoDto? = null,
    val error: String = ""
)