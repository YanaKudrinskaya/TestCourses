package com.yanakudrinskaya.domain.di

import com.yanakudrinskaya.domain.favorite.FavoriteInteractor
import com.yanakudrinskaya.domain.favorite.impl.FavoriteInteractorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

    @Binds
    @Singleton
    abstract fun bindFavoriteInteractor(
        impl: FavoriteInteractorImpl
    ): FavoriteInteractor
}