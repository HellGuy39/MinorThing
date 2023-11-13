package com.hellguy39.minor_thing.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.hellguy39.minor_thing.database.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    suspend fun getAll(): List<UserEntity>

    @Query("SELECT * FROM user")
    fun getAllFlow(): Flow<List<UserEntity>>

    @Query("SELECT * FROM user WHERE id LIKE :id LIMIT 1")
    suspend fun findById(id: Int): UserEntity?

    @Query("SELECT * FROM user WHERE login LIKE :login LIMIT 1")
    suspend fun findByLogin(login: String): UserEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userEntity: UserEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg userEntities: UserEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(userEntity: UserEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateAll(vararg userEntities: UserEntity)

    @Delete
    suspend fun delete(userEntity: UserEntity)

    @Delete
    suspend fun deleteAll(vararg userEntities: UserEntity)

}