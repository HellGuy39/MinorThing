package com.hellguy39.minor_thing.model

data class RegisterParams(
    val login: String,
    val password: String,
    val userRole: UserRole
)
