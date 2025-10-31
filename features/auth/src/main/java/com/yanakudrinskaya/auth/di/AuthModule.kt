package com.yanakudrinskaya.auth.di

import com.yanakudrinskaya.auth.ui.view_model.LoginViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val authModule = module {
    viewModel { LoginViewModel(get(), get()) }
}