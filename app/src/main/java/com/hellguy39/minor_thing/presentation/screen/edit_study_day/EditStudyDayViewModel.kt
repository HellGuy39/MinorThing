package com.hellguy39.minor_thing.presentation.screen.edit_study_day

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hellguy39.minor_thing.domain.TimetableRepository
import com.hellguy39.minor_thing.model.StudyDay
import com.hellguy39.minor_thing.presentation.navigation.ARG_ID_EMPTY
import com.hellguy39.minor_thing.presentation.navigation.ARG_ID_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditStudyDayViewModel
@Inject
constructor(
    private val timetableRepository: TimetableRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val id = savedStateHandle.get<Int>(ARG_ID_KEY) ?: ARG_ID_EMPTY

    private val isEdit: Boolean
        get() = id != ARG_ID_EMPTY

    private val _uiState = MutableStateFlow(EditStudyDayUiState())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.update { state -> state.copy(isEdit = isEdit) }
        if (isEdit) {
            fetchEditableStudyDay()
        }
    }

    private fun fetchEditableStudyDay() {
        viewModelScope.launch {
            val studyDay = timetableRepository.getStudyDayById(id) ?: return@launch
            _uiState.update { state ->
                state.copy(
                    createdAt = studyDay.createdAt,
                    updatedAt = studyDay.updatedAt,
                    schedule = studyDay.schedule,
                )
            }
        }
    }

    fun addNewSubject(subjectName: String) {
        val schedule = _uiState.value.schedule
            .toMutableList()
            .apply { add(subjectName) }
        _uiState.update { state ->
            state.copy(schedule = schedule, isAddItemDialogVisible = false)
        }
    }

    fun showAddSubjectDialog() {
        _uiState.update { state -> state.copy(isAddItemDialogVisible = true) }
    }

    fun dismissAddSubjectDialog() {
        _uiState.update { state -> state.copy(isAddItemDialogVisible = false) }
    }

    fun delete() {
        viewModelScope.launch {
            timetableRepository.deleteStudyDayById(id)
        }
    }

    fun showDatePickerDialog() {
        _uiState.update { state -> state.copy(isDatePickerDialogVisible = true) }
    }

    fun dismissDatePickerDialog() {
        _uiState.update { state -> state.copy(isDatePickerDialogVisible = true) }
    }

    fun selectDate(date: Long?) {
        _uiState.update { state ->
            state.copy(
                date = date ?: System.currentTimeMillis(),
                isDatePickerDialogVisible = false
            )
        }
    }

    fun save() {
        viewModelScope.launch {
            val state = _uiState.value
            val saveableStudyDay = StudyDay(
                id = if (id == ARG_ID_EMPTY) 0 else id,
                createdAt = state.createdAt,
                updatedAt = state.updatedAt,
                date = state.date,
                schedule = state.schedule
            )
            if (isEdit) {
                timetableRepository.updateStudyDay(saveableStudyDay)
            } else {
                timetableRepository.createStudyDay(saveableStudyDay)
            }
        }
    }
}

data class EditStudyDayUiState(
    val isEdit: Boolean = false,
    val isAddItemDialogVisible: Boolean = false,
    val isDatePickerDialogVisible: Boolean = false,
    val date: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis(),
    val createdAt: Long = System.currentTimeMillis(),
    val schedule: List<String> = listOf()
)