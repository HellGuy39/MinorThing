package com.hellguy39.minor_thing.model

data class StudyDay(
    val id: Int,
    val createdAt: Long,
    val updatedAt: Long,
    val date: Long,
    val schedule: List<String>
)
