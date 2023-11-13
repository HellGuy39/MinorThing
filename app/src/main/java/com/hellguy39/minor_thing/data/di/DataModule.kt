package com.hellguy39.minor_thing.data.di

import com.hellguy39.minor_thing.data.AuthRepositoryImpl
import com.hellguy39.minor_thing.data.TimetableRepositoryImpl
import com.hellguy39.minor_thing.domain.AuthRepository
import com.hellguy39.minor_thing.domain.TimetableRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    fun bindTimetableRepository(
        timetableRepositoryImpl: TimetableRepositoryImpl
    ): TimetableRepository

}