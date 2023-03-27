package com.stovepos.stoveorderingandroidapp.network

import com.stovepos.stoveorderingandroidapp.data.remote.venue_info_dto.VenueInfoDto
import com.stovepos.stoveorderingandroidapp.repository.VenueInfoRepository
import com.stovepos.stoveorderingandroidapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject





class GetVenueInfo @Inject constructor(
    private val venueInfoRepository: VenueInfoRepository
) {

    operator fun invoke(): Flow<Resource<VenueInfoDto>> = flow {

        try {
            emit(Resource.Loading<VenueInfoDto>())
            val venueInfo = venueInfoRepository.getVenueInfo(venueid = 23)
            emit(Resource.Success<VenueInfoDto>(venueInfo))


        }catch (e: HttpException){
            emit(Resource.Error<VenueInfoDto>(e.localizedMessage ?: "An unexpected error occurred"))

        }catch (e: IOException){
            emit(Resource.Error<VenueInfoDto>("Couldn't Reach server. check you internet connection."))
        }
    }
}