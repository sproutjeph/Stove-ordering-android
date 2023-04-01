package com.stovepos.stoveorderingandroidapp.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.stovepos.stoveorderingandroidapp.data.remote.menu_data_dto.ItemOptionsJson


class ItemOptionsJsonTypeConverter{
    @TypeConverter
    fun fromItemOptionsJson(value: List<ItemOptionsJson>): String {
        val gson = Gson()
        val type = object : TypeToken<List<ItemOptionsJson>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toItemOptionsJson(value: String): List<ItemOptionsJson> {
        val gson = Gson()
        val type = object : TypeToken<List<ItemOptionsJson>>() {}.type
        return gson.fromJson(value, type)
    }
}