package com.stovepos.stoveorderingandroidapp.repository

import com.stovepos.stoveorderingandroidapp.data.remote.venue_info_dto.VenueInfoDto

interface VenueInfoRepository {
    suspend fun getVenueInfo(venueid: Int): VenueInfoDto
}