package com.stovepos.stoveorderingandroidapp.network

import android.util.Log
import com.stovepos.stoveorderingandroidapp.data.remote.menu_data_dto.MenuDataDto
import com.stovepos.stoveorderingandroidapp.repository.MenuDataRepository
import com.stovepos.stoveorderingandroidapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMenuData @Inject constructor(
    private val menuDataRepository: MenuDataRepository
) {

    operator fun invoke(): Flow<Resource<MenuDataDto>> = flow {

        try {
            emit(Resource.Loading<MenuDataDto>())
            val menuData = menuDataRepository.getMenuData(venuid = 23)
            emit(Resource.Success<MenuDataDto>(menuData))

            Log.d("MENU_DATA", "menu items: ${menuData.menuItems.size}")

        }catch (e: HttpException){
            emit(Resource.Error<MenuDataDto>(e.localizedMessage ?: "An unexpected error occurred"))

        }catch (e: IOException){
            emit(Resource.Error<MenuDataDto>("Couldn't Reach server. check you internet connection."))
        }
    }
}