package com.yanakudrinskaya.main

import android.app.Application
import com.yanakudrinskaya.account.di.accountModule
import com.yanakudrinskaya.auth.di.authModule
import com.yanakudrinskaya.course.di.courseModule
import com.yanakudrinskaya.data.di.dataModule
import com.yanakudrinskaya.domain.di.domainModule
import com.yanakudrinskaya.favorites.di.favoritesModule
import com.yanakudrinskaya.home.di.homeModule
import com.yanakudrinskaya.main.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            androidLogger(org.koin.core.logger.Level.DEBUG)
            modules(
                dataModule,
                domainModule,
                homeModule,
                mainModule,
                courseModule,
                authModule,
                favoritesModule,
                accountModule,
            )
        }
    }
}