package com.stovepos.stoveorderingandroidapp.repository

import com.stovepos.stoveorderingandroidapp.data.remote.menu_data_dto.MenuDataDto

interface MenuDataRepository {
    suspend fun getMenuData(venuid: Int): MenuDataDto
}

