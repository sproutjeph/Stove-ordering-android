package com.stovepos.stoveorderingandroidapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.stovepos.stoveorderingandroidapp.data.remote.menu_data_dto.ItemOptionsJson
import java.util.*


@Entity(tableName = "menu_tbl")
data class MenuItemModel(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    val itemImagesJson: List<String>,
    val itemOptionsJson: List<ItemOptionsJson>,
    val itemMods: String,
    val itemName: String,
    val itemPrice: String,
    val itemId: Int,
    val menuCatId: Int,
    val modPrompt: Int,
    val taxRate: String,
    val itemQty:Int = 1
)




