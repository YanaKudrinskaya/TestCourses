package com.yanakudrinskaya.auth.ui.view_model

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yanakudrinskaya.auth.R
import com.yanakudrinskaya.auth.ui.state.LoginSocialState
import com.yanakudrinskaya.auth.ui.state.LoginUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import androidx.core.net.toUri
import com.yanakudrinskaya.domain.ResourcesProvider
import com.yanakudrinskaya.domain.auth.ValidateEmailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class LoginViewModel @Inject constructor(
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val resourcesProvider: ResourcesProvider
) : ViewModel() {

    private val uiState = MutableStateFlow<LoginUiState>(LoginUiState.Initial)
    fun getUiState(): StateFlow<LoginUiState> = uiState.asStateFlow()

    fun onEmailChange(email: String) = updateState { it.copy(email = email).validate() }

    fun onPasswordChange(password: String) = updateState { it.copy(password = password).validate() }

    fun login() {
        viewModelScope.launch {
            uiState.value = LoginUiState.Success
        }
    }

    private fun LoginUiState.Input.validate(): LoginUiState.Input {
        val isEmailValid = validateEmailUseCase(email)
        val isPasswordValid = password.isNotBlank()
        val isLoginButtonEnabled = isEmailValid && isPasswordValid

        return this.copy(
            isEmailValid = isEmailValid,
            isPasswordValid = isPasswordValid,
            isLoginButtonEnabled = isLoginButtonEnabled
        )
    }

    fun onSocialLoginClick(socialState: LoginSocialState) = updateState {
        it.copy(
            socialLoginIntent = createIntent(socialState),
            socialLoginErrorMessage = resourcesProvider.getString(R.string.auth_browser_not_found)
        )
    }

    private fun createIntent(socialState: LoginSocialState): Intent {
        val url = when (socialState) {
            LoginSocialState.VK -> resourcesProvider.getString(R.string.auth_vk_url)
            LoginSocialState.OK -> resourcesProvider.getString(R.string.auth_ok_url)
        }
        return Intent(Intent.ACTION_VIEW, url.toUri())
    }

    fun resetIntent() = updateState { it.copy(socialLoginIntent = null, socialLoginErrorMessage = null) }

    private inline fun updateState(transform: (LoginUiState.Input) -> LoginUiState.Input) {
        uiState.update { currentState ->
            transform(currentState.asInputState())
        }
    }

    private fun LoginUiState.asInputState(): LoginUiState.Input = when (this) {
        is LoginUiState.Input -> this
        else -> LoginUiState.Input()
    }
}