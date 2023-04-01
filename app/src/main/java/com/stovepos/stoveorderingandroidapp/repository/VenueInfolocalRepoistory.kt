package com.stovepos.stoveorderingandroidapp.repository

import com.stovepos.stoveorderingandroidapp.data.local.VenueInfoModel
import kotlinx.coroutines.flow.Flow

interface VenueInfoLocalRepository {
    fun getAllVenueInfo(): Flow<List<VenueInfoModel>>

    suspend fun addVenueInfo(venueInfoModel:List<VenueInfoModel>)

    suspend fun deleteVenueInfo()

}