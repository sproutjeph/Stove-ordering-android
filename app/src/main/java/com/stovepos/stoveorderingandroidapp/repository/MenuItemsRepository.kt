package com.stovepos.stoveorderingandroidapp.repository

import com.stovepos.stoveorderingandroidapp.data.local.MenuCatButtonModel
import com.stovepos.stoveorderingandroidapp.data.local.MenuItemModel
import kotlinx.coroutines.flow.Flow

interface MenuItemsRepository {
    fun getAllMenuItems(): Flow<List<MenuItemModel>>

    fun getAllMenuCatButtons(): Flow<List<MenuCatButtonModel>>

    suspend fun getMenuItemById(menuItemId: Int): MenuItemModel?
}