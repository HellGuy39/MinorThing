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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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

    override fun getCurrentUserFlow(): Flow<User?> {
        return userDao.findByIdFlow(localStorage.authenticatedUserid)
            .map { it?.toUser() }
    }

    override suspend fun register(registerParams: RegisterParams): Boolean {
        delay(FAKE_DELAY)

        if (userDao.findByLogin(registerParams.login) != null) return false

        val userEntity = UserEntity(
            login = registerParams.login,
            password = registerParams.password,
            userRoleTag = registerParams.userRole.toString()
        )
        val id = userDao.insert(userEntity).toInt()
        localStorage.authenticatedUserid = id
        return true
    }

    override suspend fun login(loginParams: LoginParams): Boolean {
        delay(FAKE_DELAY)

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

    companion object {
        private const val FAKE_DELAY = 3_000L
    }
}