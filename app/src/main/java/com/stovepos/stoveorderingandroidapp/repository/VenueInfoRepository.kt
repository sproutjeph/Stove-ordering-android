package com.stovepos.stoveorderingandroidapp.repository

import com.stovepos.stoveorderingandroidapp.data.local.MenuItemModel
import com.stovepos.stoveorderingandroidapp.data.remote.venue_info_dto.VenueInfoDto
import kotlinx.coroutines.flow.Flow

interface VenueInfoRepository {
    suspend fun getVenueInfo(venueid: Int): VenueInfoDto
}