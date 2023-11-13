package com.hellguy39.minor_thing.database.type_converter

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class MapOfStringsConverter {
    private val moshi = Moshi.Builder().build()
    private val membersType = Types
        .newParameterizedType(Map::class.java, String::class.java, String::class.java)
    private val mapOfStringsAdapter = moshi.adapter<Map<String, String>>(membersType)

    @TypeConverter
    fun stringToMapOfStrings(string: String): Map<String, String> {
        return mapOfStringsAdapter.fromJson(string).orEmpty()
    }

    @TypeConverter
    fun mapOfStringsToString(map: Map<String, String>): String {
        return mapOfStringsAdapter.toJson(map)
    }
}