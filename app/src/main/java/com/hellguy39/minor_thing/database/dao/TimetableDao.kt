package com.hellguy39.minor_thing.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.hellguy39.minor_thing.database.entity.StudyDayEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TimetableDao {

    @Query("SELECT * FROM study_day")
    suspend fun getAll(): List<StudyDayEntity>

    @Query("SELECT * FROM study_day")
    fun getAllFlow(): Flow<List<StudyDayEntity>>

    @Query("SELECT * FROM study_day WHERE id LIKE :id LIMIT 1")
    suspend fun findById(id: Int): StudyDayEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(studyDayEntity: StudyDayEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg studyDayEntities: StudyDayEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(studyDayEntity: StudyDayEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateAll(vararg studyDayEntities: StudyDayEntity)

    @Delete
    suspend fun delete(studyDayEntity: StudyDayEntity)

    @Delete
    suspend fun deleteAll(vararg studyDayEntities: StudyDayEntity)

}