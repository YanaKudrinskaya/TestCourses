package com.yanakudrinskaya.data.di

import androidx.room.Room
import com.google.gson.Gson
import com.yanakudrinskaya.core.models.Course
import com.yanakudrinskaya.core.utils.ResourcesProvider
import com.yanakudrinskaya.data.NetworkClient
import com.yanakudrinskaya.data.db.AppDatabase
import com.yanakudrinskaya.data.db.dao.FavoriteCourseDao
import com.yanakudrinskaya.data.mappers.FavoriteCourseMapper
import com.yanakudrinskaya.data.network.LocalJsonClient
import com.yanakudrinskaya.data.repository.CoursesRepositoryImpl
import com.yanakudrinskaya.data.repository.FavoriteRepositoryImpl
import com.yanakudrinskaya.data.repository.ResourcesProviderImpl
import com.yanakudrinskaya.domain.courses.api.CoursesRepository
import com.yanakudrinskaya.domain.favorite.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {

    single<ResourcesProvider> { ResourcesProviderImpl(get()) }

    factory { Gson() }

    single<NetworkClient> {
        LocalJsonClient()
    }

//    single<AppDatabase> {
//        Room.databaseBuilder(
//            androidContext(),
//            AppDatabase::class.java,
//            "courses.db"
//        )
//            .fallbackToDestructiveMigration()
//            .build()
//    }

//    single<FavoriteCourseDao> {
//        get<AppDatabase>().favoriteCourseDao()
//    }

//    factory { FavoriteCourseMapper() }

    single<CoursesRepository> {
        CoursesRepositoryImpl(get(), get())
    }

    single<FavoriteRepository> {
        FavoriteRepositoryImpl()
    }
}
