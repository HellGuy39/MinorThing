package com.hellguy39.minor_thing.data

import com.hellguy39.minor_thing.data.mapper.toUser
import com.hellguy39.minor_thing.database.dao.UserDao
import com.hellguy39.minor_thing.database.entity.UserEntity
import com.hellguy39.minor_thing.domain.AuthRepository
import com.hellguy39.minor_thing.model.LoginParams
import com.hellguy39.minor_thing.model.RegisterParams
import com.hellguy39.minor_thing.model.User
import com.hellguy39.minor_thing.prefs.SharedPreferencesLocalStorage
import kotlinx.coroutines.delay
import javax.inject.Inject

class AuthRepositoryImpl
@Inject
constructor(
    private val userDao: UserDao,
    private val localStorage: SharedPreferencesLocalStorage
): AuthRepository {

    override suspend fun getCurrentUser(): User? {
        if (!isAuthenticated()) return null
        return userDao.findById(localStorage.authenticatedUserid)?.toUser()
    }

    override suspend fun register(registerParams: RegisterParams): Boolean {
        delay(3000)

        if (userDao.findByLogin(registerParams.login) != null) return false

        val userEntity = UserEntity(
            login = registerParams.login,
            password = registerParams.password,
            accountType = registerParams.accountType.toString()
        )
        val id = userDao.insert(userEntity).toInt()
        localStorage.authenticatedUserid = id
        return true
    }

    override suspend fun login(loginParams: LoginParams): Boolean {
        delay(3000)

        val fundedUserEntity = userDao.findByLogin(loginParams.login) ?: return false

        return if (fundedUserEntity.password == loginParams.password) {
            localStorage.authenticatedUserid = fundedUserEntity.id
            true
        } else {
            false
        }
    }

    override suspend fun isAuthenticated(): Boolean {
        return localStorage.authenticatedUserid != SharedPreferencesLocalStorage.DefaultValues.emptyUserId
    }

    override suspend fun logout() {
        localStorage.authenticatedUserid = SharedPreferencesLocalStorage.DefaultValues.emptyUserId
    }
}