package com.example.stacksaveapp.viewmodel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// Represents the different states the UI can be in
sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    data class Success(val userId: String) : AuthState()
    data class Error(val message: String) : AuthState()
}

class AuthViewModel(
    private val authRepository: AuthRepository = AuthRepository(),
    private val userRepository: UserRepository = UserRepository()
) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    fun registerUser(email: String, pass: String, categories: List<String>) {
        _authState.value = AuthState.Loading

        viewModelScope.launch {
            // 1. Create the user in Firebase Auth
            val authResult = authRepository.registerWithEmail(email, pass)

            authResult.onSuccess { userId ->
                // 2. If successful, create the corresponding document in Firestore
                val profileResult = userRepository.createInitialUserProfile(userId, email, categories)

                profileResult.onSuccess {
                    _authState.value = AuthState.Success(userId)
                }.onFailure { error ->
                    _authState.value = AuthState.Error(error.message ?: "Failed to create profile")
                }
            }.onFailure { error ->
                _authState.value = AuthState.Error(error.message ?: "Registration failed")
            }
        }
    }
}