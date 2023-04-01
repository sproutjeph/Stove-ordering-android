package com.stovepos.stoveorderingandroidapp.network

import androidx.room.withTransaction
import com.stovepos.stoveorderingandroidapp.data.local.MenuItemDatabase
import com.stovepos.stoveorderingandroidapp.data.mapper.toMenuCatButton
import com.stovepos.stoveorderingandroidapp.data.mapper.toMenuItemModel
import com.stovepos.stoveorderingandroidapp.repository.MenuDataRepository
import com.stovepos.stoveorderingandroidapp.utils.Constants.VENUE_ID
import com.stovepos.stoveorderingandroidapp.utils.networkBoundResource
import kotlinx.coroutines.delay
import javax.inject.Inject

class GetMenuData @Inject constructor(
    private val menuDataRepository: MenuDataRepository,
    private val db: MenuItemDatabase
) {

    private val menuItemDao = db.menuItemDao
    private val menuCatButtonDao = db.menuCatButtonDao


    operator fun invoke() = networkBoundResource(
        query = {
            menuItemDao.getAllMenuItem()
        },
        fetch = {
            delay(2000)
            menuDataRepository.getMenuData(venuid = VENUE_ID)
        },
        saveFetchResult = { menuData ->
            db.withTransaction {
                menuItemDao.deleteAllMenuItems()
                menuItemDao.addMenuItem(
                    menuData.menuItems.map{ menuItem ->
                        menuItem.toMenuItemModel()
                    }
                )
            }

            db.withTransaction {
                menuCatButtonDao.deleteAllMenuCatButtons()
                menuCatButtonDao.addMenuCartButton(
                    menuData.menuCat.map { menuCatButton ->
                        menuCatButton.toMenuCatButton()
                    }
                )
            }

        }
    )
}