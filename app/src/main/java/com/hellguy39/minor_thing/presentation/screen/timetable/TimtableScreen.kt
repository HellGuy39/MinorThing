package com.hellguy39.minor_thing.presentation.screen.timetable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hellguy39.minor_thing.R
import com.hellguy39.minor_thing.model.UserRole
import com.hellguy39.minor_thing.presentation.navigation.ARG_ID_EMPTY
import com.hellguy39.minor_thing.ui.util.calculateScheduledSubjectTime
import com.hellguy39.minor_thing.ui.util.millisToDisplayableDate
import com.hellguy39.minor_thing.ui.values.Spaces

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimetableScreen(
    navigateToEdit: (id: Int) -> Unit,
    navigateToLogin: () -> Unit,
    timetableViewModel: TimetableViewModel = hiltViewModel()
) {
    val uiState by timetableViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        timetableViewModel.loadData()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Welcome back, ${uiState.currentUser?.login}!"
                    )
                },
                actions = {
                    IconButton(
                        onClick = {
                            timetableViewModel.logout()
                            navigateToLogin()
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_logout_24),
                            contentDescription = null
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            if (uiState.currentUser?.userRole is UserRole.Teacher) {
                FloatingActionButton(onClick = { navigateToEdit(ARG_ID_EMPTY) }) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_add_24),
                        contentDescription = null
                    )
                }
            }
        }
    ) { innerPadding ->
        if (uiState.studyDays.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.padding(Spaces.extraLarge),
                    verticalArrangement = Arrangement.spacedBy(Spaces.medium),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        modifier = Modifier.size(64.dp),
                        painter = painterResource(id = R.drawable.baseline_today_24),
                        contentDescription = null
                    )
                    Text(text = "So far there is no data here...")
                }
            }
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = Spaces.medium),
            contentPadding = innerPadding,
            verticalArrangement = Arrangement.spacedBy(Spaces.medium)
        ) {
            items(uiState.studyDays) { item ->
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = Spaces.medium),
                    onClick = { navigateToEdit(item.id) }
                ) {
                    Column(
                        modifier = Modifier.padding(Spaces.medium),
                        verticalArrangement = Arrangement.spacedBy(Spaces.small)
                    ) {
                        Text(text = millisToDisplayableDate(item.date))
                        Divider()
                        item.schedule.forEachIndexed { index, subjectName ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(text = calculateScheduledSubjectTime(index))
                                Text(text = subjectName)
                            }
                        }
                    }
                }
            }
        }
    }
}