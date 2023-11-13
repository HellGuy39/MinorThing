package com.hellguy39.minor_thing.presentation.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hellguy39.minor_thing.domain.AuthRepository
import com.hellguy39.minor_thing.model.LoginParams
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class LoginViewModel
@Inject
constructor(
    private val authRepository: AuthRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val isAuthenticated = authRepository.isAuthenticated()
            _uiState.update { state -> state.copy(isAuthenticated = isAuthenticated) }
        }
    }

    fun login() {

        if (_uiState.value.isLoading) return
        toggleLoading()

        viewModelScope.launch {

            val params = LoginParams(
                login = _uiState.value.login,
                password = _uiState.value.password
            )

            val isAuthenticated = authRepository.login(params)

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

    private fun toggleLoading() {
        _uiState.update { state -> state.copy(isLoading = state.isLoading.not()) }
    }

}

data class LoginUiState(
    val isLoading: Boolean = false,
    val isAuthenticated: Boolean = false,
    val login: String = "",
    val password: String = ""
)