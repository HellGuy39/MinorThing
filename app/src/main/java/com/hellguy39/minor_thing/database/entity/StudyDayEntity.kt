package com.hellguy39.minor_thing.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = StudyDayEntity.TABLE_NAME)
data class StudyDayEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val createdAt: Long,
    val updatedAt: Long,
    val dayName: String,
    val placeName: String,
    val teacherName: String,
    val schedule: Map<String, String>
) {
    companion object {
        const val TABLE_NAME = "study_day"
    }
}

