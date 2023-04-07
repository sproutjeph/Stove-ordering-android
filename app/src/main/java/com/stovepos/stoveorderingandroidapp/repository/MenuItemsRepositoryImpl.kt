package com.stovepos.stoveorderingandroidapp.repository

import com.stovepos.stoveorderingandroidapp.data.local.MenuCatButtonDao
import com.stovepos.stoveorderingandroidapp.data.local.MenuCatButtonModel
import com.stovepos.stoveorderingandroidapp.data.local.MenuItemDao
import com.stovepos.stoveorderingandroidapp.data.local.MenuItemModel
import kotlinx.coroutines.flow.Flow

class MenuItemsRepositoryImpl (
    private val menuItemsDao: MenuItemDao,
    private val menuCatButtonDao: MenuCatButtonDao
): MenuItemsRepository {
    override fun getAllMenuItems(): Flow<List<MenuItemModel>> {
        return menuItemsDao.getAllMenuItem()
    }

    override suspend fun getMenuItemById(menuItemId: Int): MenuItemModel {
        return menuItemsDao.getMenuItemById(menuItemId)
    }

    override fun getAllMenuCatButtons(): Flow<List<MenuCatButtonModel>> {
        return menuCatButtonDao.getAllMenuCatButtons()
    }
}