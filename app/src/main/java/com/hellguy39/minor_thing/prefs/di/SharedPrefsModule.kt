package com.hellguy39.minor_thing.prefs.di

import android.content.Context
import com.hellguy39.minor_thing.prefs.SharedPreferencesLocalStorage
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object SharedPrefsModule {

    fun provideSharedPreferencesLocalStorage(
        @ApplicationContext context: Context
    ): SharedPreferencesLocalStorage {
        return SharedPreferencesLocalStorage(context)
    }

}