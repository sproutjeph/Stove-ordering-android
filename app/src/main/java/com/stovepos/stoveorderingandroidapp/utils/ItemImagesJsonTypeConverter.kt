package com.stovepos.stoveorderingandroidapp.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ItemImagesJsonTypeConverter{
    @TypeConverter
    fun fromItemImagesJson(value: List<String>): String {
        val gson = Gson()
        val type = object : TypeToken<List<String>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toItemImagesJson(value: String): List<String> {
        val gson = Gson()
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(value, type)
    }
}