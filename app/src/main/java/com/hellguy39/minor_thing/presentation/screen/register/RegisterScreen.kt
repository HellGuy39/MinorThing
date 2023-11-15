package com.hellguy39.minor_thing.presentation.screen.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hellguy39.minor_thing.R
import com.hellguy39.minor_thing.model.AccountType
import com.hellguy39.minor_thing.ui.values.Spaces

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    navigateToTimetable: () -> Unit,
    navigateBack: () -> Unit,
    registerViewModel: RegisterViewModel = hiltViewModel()
) {
    val uiState by registerViewModel.uiState.collectAsStateWithLifecycle()
    var isPasswordVisible by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = uiState.isAuthenticated) {
        if (uiState.isAuthenticated) {
            navigateToTimetable()
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {  },
                navigationIcon = {
                    IconButton(
                        onClick = { navigateBack() }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(top = innerPadding.calculateTopPadding())
                .padding(horizontal = Spaces.extraLarge, vertical = Spaces.extraLarge)
                .imePadding()
                .navigationBarsPadding(),
            verticalArrangement = Arrangement.spacedBy(Spaces.medium),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                modifier = Modifier.size(64.dp),
                painter = painterResource(id = R.drawable.baseline_app_registration_24),
                contentDescription = null
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(Spaces.small)
            ) {
                Text(text = "Status")
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(Spaces.small)
                ) {
                    FilterChip(
                        modifier = Modifier.height(FilterChipDefaults.Height).weight(1f),
                        selected = uiState.accountType == AccountType.Student,
                        onClick = { registerViewModel.editAccountType(AccountType.Student) },
                        label = { Text(text = "Student") }
                    )
                    FilterChip(
                        modifier = Modifier.height(FilterChipDefaults.Height).weight(1f),
                        selected = uiState.accountType == AccountType.Teacher,
                        onClick = { registerViewModel.editAccountType(AccountType.Teacher) },
                        label = { Text(text = "Teacher") }
                    )
                }
            }
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = uiState.login,
                onValueChange = { text -> registerViewModel.editLogin(text)},
                placeholder = { Text("Login") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = uiState.password,
                onValueChange = { text -> registerViewModel.editPassword(text)},
                label = { Text("Password") },
                visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    val iconId = if (isPasswordVisible) R.drawable.outline_visibility_24 else R.drawable.outline_visibility_off_24
                    IconButton(onClick = { isPasswordVisible = isPasswordVisible.not() }) {
                        Icon(
                            painter = painterResource(id = iconId),
                            contentDescription = null
                        )
                    }
                }
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { registerViewModel.register() },
                    enabled = !uiState.isLoading
                ) {
                    if (uiState.isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(20.dp)
                        )
                    } else {
                        Text("Register")
                    }
                }
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}