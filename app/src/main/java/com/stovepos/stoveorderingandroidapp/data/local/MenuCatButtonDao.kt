package com.stovepos.stoveorderingandroidapp.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface MenuCatButtonDao {
    @Query("SELECT * from menu_button_tbl")
    fun getAllMenuCatButtons(): Flow<List<MenuCatButtonModel>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMenuCartButton(menuCatButtonModel: List<MenuCatButtonModel>)

    @Query("DELETE from menu_button_tbl")
    suspend fun deleteAllMenuCatButtons()


}