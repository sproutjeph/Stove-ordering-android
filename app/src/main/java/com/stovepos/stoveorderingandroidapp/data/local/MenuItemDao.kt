package com.stovepos.stoveorderingandroidapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MenuItemDao {
    @Query("SELECT * from menu_tbl")
    fun getAllMenuItem(): Flow<List<MenuItemModel>>

    @Query("SELECT * from menu_tbl where itemId =:itemId")
    suspend fun getMenuItemById(itemId:Int): MenuItemModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMenuItem(menuItem: List<MenuItemModel>)

    @Query("DELETE from menu_tbl")
    suspend fun deleteAllMenuItems()

    @Query("SELECT * FROM menu_tbl where menuCatId =:menuCatId")
    fun getMenuItemsByCategoryId(menuCatId: Int): Flow<List<MenuItemModel>>

}