package com.stovepos.stoveorderingandroidapp.repository

import com.stovepos.stoveorderingandroidapp.data.local.VenueInfoDao
import com.stovepos.stoveorderingandroidapp.data.local.VenueInfoModel
import kotlinx.coroutines.flow.Flow

class VenueInfoLocalRepositoryImpl(
    private val venueInfoDao: VenueInfoDao
): VenueInfoLocalRepository {
    override fun getAllVenueInfo(): Flow<List<VenueInfoModel>> {
        return venueInfoDao.getVenueInfo()
    }

    override suspend fun addVenueInfo(venueInfoModel: List<VenueInfoModel>) {
        return venueInfoDao.addVenueInfo(venueInfoModel)
    }

    override suspend fun deleteVenueInfo() {
        return venueInfoDao.deleteVenueInfo()
    }
}