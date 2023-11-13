package com.hellguy39.minor_thing.domain

import com.hellguy39.minor_thing.model.LoginParams
import com.hellguy39.minor_thing.model.RegisterParams
import com.hellguy39.minor_thing.model.User

interface AuthRepository {

    suspend fun getCurrentUser(): User?

    suspend fun register(registerParams: RegisterParams): Boolean

    suspend fun login(loginParams: LoginParams): Boolean

    suspend fun isAuthenticated(): Boolean

    suspend fun logout()

}