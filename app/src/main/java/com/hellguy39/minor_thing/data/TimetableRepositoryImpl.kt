package com.hellguy39.minor_thing.data

import com.hellguy39.minor_thing.data.mapper.toStudyDay
import com.hellguy39.minor_thing.data.mapper.toStudyDayEntity
import com.hellguy39.minor_thing.database.dao.TimetableDao
import com.hellguy39.minor_thing.domain.TimetableRepository
import com.hellguy39.minor_thing.model.StudyDay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TimetableRepositoryImpl
@Inject
constructor(
    private val timetableDao: TimetableDao
): TimetableRepository {

    override suspend fun getStudyDays(): List<StudyDay> {
        return timetableDao.getAll()
            .map { it.toStudyDay() }
    }

    override fun getStudyDaysFlow(): Flow<List<StudyDay>> {
        return timetableDao.getAllFlow()
            .map { list -> list.map { it.toStudyDay() } }
    }

    override suspend fun createStudyDay(studyDay: StudyDay) {
        timetableDao.insert(studyDay.toStudyDayEntity())
    }

    override suspend fun updateStudyDay(studyDay: StudyDay) {
        timetableDao.update(studyDay.toStudyDayEntity())
    }

    override suspend fun deleteStudyDay(studyDay: StudyDay) {
        timetableDao.delete(studyDay.toStudyDayEntity())
    }
}