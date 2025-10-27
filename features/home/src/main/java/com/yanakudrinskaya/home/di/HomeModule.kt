package com.yanakudrinskaya.home.di

import com.yanakudrinskaya.home.ui.view_model.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {

    viewModel { HomeViewModel(get(), get(), get()) }
}