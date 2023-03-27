package com.stovepos.stoveorderingandroidapp.repository

import com.stovepos.stoveorderingandroidapp.data.remote.venue_info_dto.VenueInfoDto
import com.stovepos.stoveorderingandroidapp.network.StoveApi
import javax.inject.Inject




class VenueInfoRepositoryImpl @Inject constructor(
    private val api: StoveApi
): VenueInfoRepository {
    override suspend fun getVenueInfo(venueid: Int): VenueInfoDto {
        return api.getVenueInfo(venueid)
    }
}
