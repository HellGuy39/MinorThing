package com.hellguy39.minor_thing.presentation.screen.edit_study_day

import androidx.lifecycle.ViewModel
import com.hellguy39.minor_thing.domain.TimetableRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditStudyDayViewModel
@Inject
constructor(
    private val timetableRepository: TimetableRepository
): ViewModel() {

}