package com.hellguy39.minor_thing.data

import com.hellguy39.minor_thing.domain.TimetableRepository
import com.hellguy39.minor_thing.model.StudyDay

class TimetableRepositoryImpl: TimetableRepository {
    override suspend fun getStudyDays(): List<StudyDay> {
        TODO("Not yet implemented")
    }

    override suspend fun createStudyDay(studyDay: StudyDay) {
        TODO("Not yet implemented")
    }

    override suspend fun updateStudyDay(studyDay: StudyDay) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteStudyDay(studyDay: StudyDay) {
        TODO("Not yet implemented")
    }
}