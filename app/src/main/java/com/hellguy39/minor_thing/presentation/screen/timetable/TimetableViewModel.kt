package com.hellguy39.minor_thing.presentation.screen.timetable

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hellguy39.minor_thing.domain.AuthRepository
import com.hellguy39.minor_thing.domain.TimetableRepository
import com.hellguy39.minor_thing.model.StudyDay
import com.hellguy39.minor_thing.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TimetableViewModel
@Inject
constructor(
    private val authRepository: AuthRepository,
    timetableRepository: TimetableRepository
): ViewModel() {

    val uiState = combine(
            timetableRepository.getStudyDaysFlow(),
            authRepository.getCurrentUserFlow()
        ) { studyDays, user ->
            TimetableUiState(
                currentUser = user,
                studyDays = studyDays
            )
        }
        .stateIn(
            initialValue = TimetableUiState(),
            started = SharingStarted.WhileSubscribed(5_000),
            scope = viewModelScope
        )

    fun logout() {
        viewModelScope.launch {
            authRepository.logout()
        }
    }

}

data class TimetableUiState(
    val currentUser: User? = null,
    val studyDays: List<StudyDay> = listOf()
)