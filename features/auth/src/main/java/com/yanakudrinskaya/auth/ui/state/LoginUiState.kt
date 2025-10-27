package com.yanakudrinskaya.auth.ui.state

import android.content.Intent

internal sealed class LoginUiState {
    data object Initial : LoginUiState()

    data class Input(
        val email: String = "",
        val password: String = "",
        val isEmailValid: Boolean = false,
        val isPasswordValid: Boolean = false,
        val isLoginButtonEnabled: Boolean = false,
        val socialLoginIntent: Intent? = null,
        val socialLoginErrorMessage: String? = null,
    ) : LoginUiState()

    data object Success : LoginUiState()

    data class Error(val message: String) : LoginUiState()
}