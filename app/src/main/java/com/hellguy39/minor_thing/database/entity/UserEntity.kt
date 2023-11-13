package com.hellguy39.minor_thing.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = UserEntity.TABLE_NAME)
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val login: String,
    val password: String,
    val accountType: String
) {
    companion object {
        const val TABLE_NAME = "user"
    }
}
