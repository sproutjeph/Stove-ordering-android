package com.stovepos.stoveorderingandroidapp.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface VenueInfoDao {
    @Query("SELECT * from venue_tbl")
    fun getVenueInfo(): Flow<List<VenueInfoModel>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addVenueInfo(venueInfoModel:List<VenueInfoModel>)

    @Query("DELETE from venue_tbl")
    suspend fun deleteVenueInfo()

}