package com.hellguy39.minor_thing.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.hellguy39.minor_thing.database.dao.UserDao
import com.hellguy39.minor_thing.database.dao.TimetableDao
import com.hellguy39.minor_thing.database.entity.StudyDayEntity
import com.hellguy39.minor_thing.database.entity.UserEntity
import com.hellguy39.minor_thing.database.type_converter.ListOfStringsConverter
import com.hellguy39.minor_thing.database.type_converter.MapOfStringsConverter

@Database(
    version = 1,
    entities = [
        UserEntity::class,
        StudyDayEntity::class
    ]
)
@TypeConverters(MapOfStringsConverter::class, ListOfStringsConverter::class)
abstract class MinorThingDatabase: RoomDatabase() {

    abstract val timetableDao: TimetableDao

    abstract val userDao: UserDao

    companion object {
        const val NAME = "minor-thing-database"
    }
}