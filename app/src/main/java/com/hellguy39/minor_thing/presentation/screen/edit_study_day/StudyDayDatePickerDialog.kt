package com.hellguy39.minor_thing.presentation.screen.edit_study_day

import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudyDayDatePickerDialog(
    isVisible: Boolean,
    initialSelectedDisplayMillis: Long,
    onDateSelected: (millis: Long?) -> Unit,
    onDismiss: () -> Unit
) {
    if (!isVisible) return

    val datePickerState = rememberDatePickerState(
        initialDisplayedMonthMillis = initialSelectedDisplayMillis
    )

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(onClick = {
                onDateSelected(datePickerState.selectedDateMillis)
            }) {
                Text(text = "Confirm")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}