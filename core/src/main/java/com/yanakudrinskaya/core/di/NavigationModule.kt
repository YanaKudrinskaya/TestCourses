package com.yanakudrinskaya.core.di

import com.yanakudrinskaya.core.navigation.AppNavigatorImpl
import com.yanakudrinskaya.core.navigation.NavigationContract
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NavigationModule {

    @Binds
    @Singleton
    abstract fun bindNavigationContract(
        impl: AppNavigatorImpl
    ): NavigationContract
}