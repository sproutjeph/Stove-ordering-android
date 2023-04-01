package com.stovepos.stoveorderingandroidapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "menu_button_tbl")
data class MenuCatButtonModel(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    val menuName: String,
    val menuCat: Int,
    val sortOrder: Int,
)
