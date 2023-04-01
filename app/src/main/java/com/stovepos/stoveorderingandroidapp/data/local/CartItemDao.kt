package com.stovepos.stoveorderingandroidapp.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow



@Dao
interface CartItemDao {
    @Query("SELECT * from cart_tbl")
    fun getAllCartItems(): Flow<List<CartItemModel>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCartItem(cartItem: CartItemModel)

    @Query("DELETE from cart_tbl")
    suspend fun deleteAllCartItems()

    @Delete
    suspend fun deleteCartItem(cartItem: CartItemModel)


}