package com.hellguy39.minor_thing.model

data class User(
    val id: Int,
    val login: String,
    val password: String,
    val accountType: AccountType
)
