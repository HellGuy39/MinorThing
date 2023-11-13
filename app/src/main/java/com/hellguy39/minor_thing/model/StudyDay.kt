package com.hellguy39.minor_thing.model

data class StudyDay(
    val id: Int,
    val createdAt: Long,
    val updatedAt: Long,
    val dayName: String,
    val placeName: String,
    val teacherName: String,
    val schedule: Map<String, String>
)
