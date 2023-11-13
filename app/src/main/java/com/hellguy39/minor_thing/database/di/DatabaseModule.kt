package com.hellguy39.minor_thing.database.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hellguy39.minor_thing.database.MinorThingDatabase
import com.hellguy39.minor_thing.database.dao.TimetableDao
import com.hellguy39.minor_thing.database.dao.UserDao
import com.hellguy39.minor_thing.database.type_converter.MapOfStringsConverter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MinorThingDatabase {
        return Room.databaseBuilder(
            context,
            MinorThingDatabase::class.java,
            MinorThingDatabase.NAME
        )
            .setJournalMode(RoomDatabase.JournalMode.TRUNCATE)
            .addMigrations()
            .build()
    }

    @Provides
    fun provideTimetableDao(database: MinorThingDatabase): TimetableDao {
        return database.timetableDao
    }

    @Provides
    fun provideUserDao(database: MinorThingDatabase): UserDao {
        return database.userDao
    }

}