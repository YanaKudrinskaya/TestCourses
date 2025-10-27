package com.yanakudrinskaya.auth.ui.fragment

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.yanakudrinskaya.auth.databinding.FragmentLoginBinding
import com.yanakudrinskaya.auth.ui.state.LoginSocialState
import com.yanakudrinskaya.auth.ui.state.LoginUiState
import com.yanakudrinskaya.auth.ui.view_model.LoginViewModel
import com.yanakudrinskaya.core.navigation.NavigationContract
import com.yanakudrinskaya.core.navigation.NavigationVisibilityController
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<LoginViewModel>()

    private val navigationContract: NavigationContract?
        get() = activity as? NavigationContract

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? NavigationVisibilityController)?.setNavigationVisibility(false)

        setupListeners()
        setupObserver()
    }

    private fun setupObserver() {
        viewModel.getUiState()
            .onEach { state ->
                when (state) {
                    LoginUiState.Initial -> {
                        binding.btnLogin.isEnabled = false
                    }
                    is LoginUiState.Input -> {
                        showInputState(state)
                        if (state.socialLoginIntent != null) {
                            openSocialLoginIntent(state.socialLoginIntent, state.socialLoginErrorMessage)
                            viewModel.resetIntent()
                        }
                    }
                    LoginUiState.Success -> {
                        navigateToMainScreen()
                    }
                    is LoginUiState.Error -> {
                        showErrorState(state.message)
                    }
                }
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun setupListeners() {
        binding.apply {
            etEmail.addTextChangedListener { text ->
                viewModel.onEmailChange(text?.toString() ?: "")
            }

            etPassword.addTextChangedListener { text ->
                viewModel.onPasswordChange(text?.toString() ?: "")
            }

            btnLogin.setOnClickListener {
                viewModel.login()
            }

            ivVk.setOnClickListener {
                viewModel.onSocialLoginClick(LoginSocialState.VK)
            }

            ivOk.setOnClickListener {
                viewModel.onSocialLoginClick(LoginSocialState.OK)
            }

            tvRegister.setOnClickListener {}

            tvForgotPassword.setOnClickListener {}
        }
    }

    private fun openSocialLoginIntent(intent: Intent, message: String?) {
        try {
                startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            showErrorState("Ошибка: $message $e")
        }
    }

    private fun showInputState(state: LoginUiState.Input) {
            binding.btnLogin.isEnabled = state.isLoginButtonEnabled
    }

    private fun navigateToMainScreen() {
        navigationContract?.navigateToHome()
    }

    private fun showErrorState(message: String?) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        (activity as? NavigationVisibilityController)?.setNavigationVisibility(true)
        super.onDestroyView()
        _binding = null
    }
}
