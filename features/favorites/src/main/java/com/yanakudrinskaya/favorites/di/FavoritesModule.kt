package com.yanakudrinskaya.favorites.di

import com.yanakudrinskaya.favorites.ui.view_model.FavoriteViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val favoritesModule = module {

    viewModel {
        FavoriteViewModel(
            get()
        )
    }
}