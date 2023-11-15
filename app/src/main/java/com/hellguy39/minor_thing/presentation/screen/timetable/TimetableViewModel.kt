package com.hellguy39.minor_thing.presentation.screen.timetable

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hellguy39.minor_thing.domain.AuthRepository
import com.hellguy39.minor_thing.domain.TimetableRepository
import com.hellguy39.minor_thing.model.StudyDay
import com.hellguy39.minor_thing.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TimetableViewModel
@Inject
constructor(
    private val authRepository: AuthRepository,
    private val timetableRepository: TimetableRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(TimetableUiState())
    val uiState = _uiState.asStateFlow()

    fun loadData() {
        viewModelScope.launch {
            val studyDays = timetableRepository.getStudyDays()
            val currentUser = authRepository.getCurrentUser()
            _uiState.update { state ->
                state.copy(
                    currentUser = currentUser,
                    studyDays = studyDays,
                )
            }
        }
    }

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