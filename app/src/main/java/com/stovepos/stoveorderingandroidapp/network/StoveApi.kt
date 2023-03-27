package com.stovepos.stoveorderingandroidapp.network

import com.stovepos.stoveorderingandroidapp.data.remote.menu_data_dto.MenuDataDto
import com.stovepos.stoveorderingandroidapp.data.remote.venue_info_dto.VenueInfoDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Singleton


@Singleton
interface StoveApi {
    @GET(value = "v4/menu/getMenu")
    suspend fun getMenuData(@Query("venueid") venueid: Int): MenuDataDto

    @GET("v4/venue/getVenueInfo")
    suspend fun getVenueInfo(@Query("venueid") venueid: Int): VenueInfoDto


}


//https://pos.stovestack.com/sys/v4/menu/getMenu?venueid=1&timezone=America/Los_Angeles

//https://external.stovepos.com/sys/v4/venue/getVenueInfo?venueid=1