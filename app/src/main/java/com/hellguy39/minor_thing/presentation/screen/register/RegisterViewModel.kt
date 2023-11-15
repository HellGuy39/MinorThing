package com.hellguy39.minor_thing.presentation.screen.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hellguy39.minor_thing.domain.AuthRepository
import com.hellguy39.minor_thing.model.UserRole
import com.hellguy39.minor_thing.model.RegisterParams
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel
@Inject
constructor(
    private val authRepository: AuthRepository
): ViewModel() {

    private var _uiState = MutableStateFlow(RegisterUiState())
    val uiState = _uiState.asStateFlow()

    fun register() {
        if (_uiState.value.isLoading) return
        toggleLoading()

        viewModelScope.launch {
            val params = RegisterParams(
                login = _uiState.value.login,
                password = _uiState.value.password,
                userRole = _uiState.value.userRole
            )
            val isAuthenticated = authRepository.register(params)
            _uiState.update { state -> state.copy(isAuthenticated = isAuthenticated) }
            toggleLoading()
        }
    }

    fun editLogin(login: String) {
        _uiState.update { state -> state.copy(login = login) }
    }

    fun editPassword(password: String) {
        _uiState.update { state -> state.copy(password = password) }
    }

    fun editAccountType(userRole: UserRole) {
        _uiState.update { state -> state.copy(userRole = userRole) }
    }

    private fun toggleLoading() {
        _uiState.update { state -> state.copy(isLoading = state.isLoading.not()) }
    }
}

data class RegisterUiState(
    val isAuthenticated: Boolean = false,
    val isLoading: Boolean = false,
    val login: String = "",
    val password: String = "",
    val userRole: UserRole = UserRole.Student
)