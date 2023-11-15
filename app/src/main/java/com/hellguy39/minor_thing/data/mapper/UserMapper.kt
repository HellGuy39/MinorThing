package com.hellguy39.minor_thing.data.mapper

import com.hellguy39.minor_thing.database.entity.UserEntity
import com.hellguy39.minor_thing.model.AccountType
import com.hellguy39.minor_thing.model.User

fun UserEntity.toUser(): User {
    return User(
        id = id,
        login = login,
        password = password,
        accountType = AccountType.fromString(accountTag),
    )
}