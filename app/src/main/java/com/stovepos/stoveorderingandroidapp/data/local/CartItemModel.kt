package com.stovepos.stoveorderingandroidapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.stovepos.stoveorderingandroidapp.data.remote.menu_data_dto.Option
import java.util.*

@Entity(tableName = "cart_tbl")
data class CartItemModel(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    val itemImages_json: String,
    val itemMods: List<Option>,
    val itemName: String,
    val itemPrice: String,
    val itemId: Int,
    val menuCatId: Int,
    val itemQty:Int = 1
)



