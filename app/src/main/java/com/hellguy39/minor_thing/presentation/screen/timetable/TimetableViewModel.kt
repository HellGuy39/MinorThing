package com.hellguy39.minor_thing.presentation.screen.timetable

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hellguy39.minor_thing.domain.TimetableRepository
import com.hellguy39.minor_thing.model.StudyDay
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class TimetableViewModel
@Inject
constructor(
    timetableRepository: TimetableRepository
): ViewModel() {

    val uiState = timetableRepository
        .getStudyDaysFlow()
        .stateIn(
            initialValue = TimetableUiState(),
            started = SharingStarted.WhileSubscribed(5_000),
            scope = viewModelScope
        )

}

data class TimetableUiState(
    val studyDays: List<StudyDay> = listOf()
)