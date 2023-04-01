package com.stovepos.stoveorderingandroidapp.network

import androidx.room.withTransaction
import com.stovepos.stoveorderingandroidapp.data.local.MenuItemDatabase
import com.stovepos.stoveorderingandroidapp.data.mapper.toVenueInfo
import com.stovepos.stoveorderingandroidapp.repository.VenueInfoRepository
import com.stovepos.stoveorderingandroidapp.utils.Constants.VENUE_ID
import com.stovepos.stoveorderingandroidapp.utils.networkBoundResource
import kotlinx.coroutines.delay
import javax.inject.Inject





class GetVenueInfo @Inject constructor(
    private val venueInfoRepository: VenueInfoRepository,
    private val db: MenuItemDatabase
) {
    private val venueInfoDao = db.venueInfoDao


    operator fun invoke() = networkBoundResource(
        query = {
            venueInfoDao.getVenueInfo()
        },
        fetch = {
            delay(2000)
            venueInfoRepository.getVenueInfo(venueid = VENUE_ID)
        },
        saveFetchResult = { venueInfoData ->
            db.withTransaction {
                venueInfoDao.deleteVenueInfo()
                venueInfoDao.addVenueInfo(
                    venueInfoData.map {venueInfoItem->
                        venueInfoItem.toVenueInfo()
                    }
                )
            }



        }
    )
}