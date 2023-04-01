package com.stovepos.stoveorderingandroidapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.stovepos.stoveorderingandroidapp.utils.*

@Database(
    entities = [
        MenuItemModel::class, CartItemModel::class,
        MenuCatButtonModel::class, VenueInfoModel::class
               ],
    version = 3, exportSchema = false
)
@TypeConverters(
    DateConverter::class,
    UUIDConverter::class,
    ItemOptionsJsonTypeConverter::class,
    ItemImagesJsonTypeConverter::class,
    ItemModsTypeConverter::class
)
abstract class MenuItemDatabase: RoomDatabase() {
    abstract val menuItemDao: MenuItemDao
    abstract val cartItemDao: CartItemDao
    abstract val menuCatButtonDao: MenuCatButtonDao
    abstract val venueInfoDao: VenueInfoDao

    companion object{
        const val DATABASE_NAME = "menu_item_db"
    }
}