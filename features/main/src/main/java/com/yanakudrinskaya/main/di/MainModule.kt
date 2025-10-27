package com.yanakudrinskaya.main.di

import com.yanakudrinskaya.main.ui.MainViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

internal val mainModule = module {
    viewModel { MainViewModel() }
}