package com.stovepos.stoveorderingandroidapp.presentation.screens.home

import com.stovepos.stoveorderingandroidapp.data.remote.menu_data_dto.MenuDataDto
import com.stovepos.stoveorderingandroidapp.data.remote.venue_info_dto.VenueInfoDto

data class MenuDataState(
    val isLoading: Boolean = false,
    val menuData: MenuDataDto? = null,
    val error: String = ""
)



data class VenueInfoState(
    val isLoading: Boolean = false,
    val venueInfo: VenueInfoDto? = null,
    val error: String = ""
)