package com.hellguy39.minor_thing.data

import com.hellguy39.minor_thing.domain.AuthRepository
import com.hellguy39.minor_thing.model.LoginParams
import com.hellguy39.minor_thing.model.RegisterParams

class AuthRepositoryImpl: AuthRepository {
    override suspend fun register(registerParams: RegisterParams): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun login(loginParams: LoginParams): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun isAuthenticated(): Boolean {
        TODO("Not yet implemented")
    }
}