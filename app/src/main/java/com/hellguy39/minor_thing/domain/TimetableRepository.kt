package com.hellguy39.minor_thing.domain

import com.hellguy39.minor_thing.model.StudyDay

interface TimetableRepository {

    suspend fun getStudyDays(): List<StudyDay>

    suspend fun createStudyDay(studyDay: StudyDay)

    suspend fun updateStudyDay(studyDay: StudyDay)

    suspend fun deleteStudyDay(studyDay: StudyDay)

}