package com.stovepos.stoveorderingandroidapp.repository

import com.stovepos.stoveorderingandroidapp.network.StoveApi
import com.stovepos.stoveorderingandroidapp.data.remote.menu_data_dto.MenuDataDto
import javax.inject.Inject

class MenuDataRepositoryImpl @Inject constructor(
    private val api: StoveApi
): MenuDataRepository {
    override suspend fun getMenuData(venuid: Int): MenuDataDto {
        return api.getMenuData(venuid)
    }
}







