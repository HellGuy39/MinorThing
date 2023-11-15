package com.hellguy39.minor_thing.domain

import com.hellguy39.minor_thing.model.StudyDay
import kotlinx.coroutines.flow.Flow

interface TimetableRepository {

    suspend fun getStudyDayById(id: Int): StudyDay?

    suspend fun getStudyDays(): List<StudyDay>

    fun getStudyDaysFlow(): Flow<List<StudyDay>>

    suspend fun createStudyDay(studyDay: StudyDay)

    suspend fun updateStudyDay(studyDay: StudyDay)

    suspend fun deleteStudyDay(studyDay: StudyDay)

    suspend fun deleteStudyDayById(id: Int)

}