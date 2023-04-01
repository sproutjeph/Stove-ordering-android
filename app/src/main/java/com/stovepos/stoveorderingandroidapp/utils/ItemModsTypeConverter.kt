package com.stovepos.stoveorderingandroidapp.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.stovepos.stoveorderingandroidapp.data.remote.menu_data_dto.Option


class ItemModsTypeConverter{
    @TypeConverter
    fun fromItemModsJson(value: List<Option>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Option>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toItemModsJson(value: String): List<Option> {
        val gson = Gson()
        val type = object : TypeToken<List<Option>>() {}.type
        return gson.fromJson(value, type)
    }
}
