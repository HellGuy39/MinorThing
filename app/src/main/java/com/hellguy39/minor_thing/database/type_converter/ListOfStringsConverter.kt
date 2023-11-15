package com.hellguy39.minor_thing.database.type_converter

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class ListOfStringsConverter {

    private val moshi = Moshi.Builder().build()
    private val membersType = Types
        .newParameterizedType(List::class.java, String::class.java)
    private val mapOfStringsAdapter = moshi.adapter<List<String>>(membersType)

    @TypeConverter
    fun stringToListOfStrings(string: String): List<String> {
        return mapOfStringsAdapter.fromJson(string).orEmpty()
    }

    @TypeConverter
    fun listOfStringsToString(map: List<String>): String {
        return mapOfStringsAdapter.toJson(map)
    }
}