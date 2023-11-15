package com.hellguy39.minor_thing.data.mapper

import com.hellguy39.minor_thing.database.entity.StudyDayEntity
import com.hellguy39.minor_thing.model.StudyDay

fun StudyDayEntity.toStudyDay(): StudyDay {
    return StudyDay(
        id = id,
        createdAt = createdAt,
        updatedAt = updatedAt,
        date = date,
        schedule = schedule,
    )
}

fun StudyDay.toStudyDayEntity(): StudyDayEntity {
    return StudyDayEntity(
        id = id,
        createdAt = createdAt,
        updatedAt = updatedAt,
        date = date,
        schedule = schedule,
    )
}