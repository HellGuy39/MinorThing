package com.hellguy39.minor_thing.presentation.screen.edit_study_day

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hellguy39.minor_thing.R
import com.hellguy39.minor_thing.ui.util.calculateScheduledSubjectTime
import com.hellguy39.minor_thing.ui.util.millisToDisplayableDate
import com.hellguy39.minor_thing.ui.values.Spaces

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun EditStudyDayScreen(
    navigateBack: () -> Unit,
    editStudyDayViewModel: EditStudyDayViewModel = hiltViewModel()
) {
    val uiState by editStudyDayViewModel.uiState.collectAsStateWithLifecycle()

    AddSubjectDialog(
        isVisible = uiState.isAddItemDialogVisible,
        onDone = { subjectName -> editStudyDayViewModel.addNewSubject(subjectName) },
        onDismiss = { editStudyDayViewModel.dismissAddSubjectDialog() }
    )

    StudyDayDatePickerDialog(
        isVisible = uiState.isDatePickerDialogVisible,
        initialSelectedDisplayMillis = uiState.date,
        onDateSelected = { millis -> editStudyDayViewModel.selectDate(millis) },
        onDismiss = { editStudyDayViewModel.dismissDatePickerDialog() }
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {  },
                navigationIcon = {
                    IconButton(onClick = { navigateBack() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    if (uiState.isEdit) {
                        IconButton(
                            onClick = {
                                editStudyDayViewModel.delete()
                                navigateBack()
                            }
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_delete_outline_24),
                                contentDescription = null
                            )
                        }
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    editStudyDayViewModel.save()
                    navigateBack()
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_save_24),
                    contentDescription = null
                )
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = innerPadding.calculateTopPadding())
                .padding(horizontal = Spaces.medium, vertical = Spaces.medium)
                .imePadding()
                .navigationBarsPadding(),
            verticalArrangement = Arrangement.spacedBy(Spaces.medium)
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(Spaces.extraSmall),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Date",
                            textAlign = TextAlign.Start
                        )
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = millisToDisplayableDate(uiState.date),
                            textAlign = TextAlign.Start
                        )
                    }
                    Button(
                        modifier = Modifier,
                        onClick = { editStudyDayViewModel.showDatePickerDialog() }
                    ) {
                        Text(text = "Select")
                    }
                }
            }
            item { Divider() }
            itemsIndexed(uiState.schedule) { index, subjectName ->
                ElevatedCard(modifier = Modifier
                    .fillMaxWidth()
                    .animateItemPlacement()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(Spaces.medium),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = calculateScheduledSubjectTime(index))
                        Text(text = subjectName)
                    }
                }
            }
            item {
                ElevatedCard(
                    onClick = { editStudyDayViewModel.showAddSubjectDialog() }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(Spaces.medium),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Add new")
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_add_24),
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}