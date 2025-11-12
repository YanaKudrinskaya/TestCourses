package com.yanakudrinskaya.testcourses.di

import com.yanakudrinskaya.core.navigation.NavigationContract
import com.yanakudrinskaya.testcourses.navigation.AppNavigator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class NavigationModule {

    @Binds
    abstract fun bindNavigationContract(impl: AppNavigator): NavigationContract
}