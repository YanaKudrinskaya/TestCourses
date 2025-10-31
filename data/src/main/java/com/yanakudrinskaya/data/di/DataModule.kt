package com.yanakudrinskaya.data.di

import androidx.room.Room
import com.google.gson.Gson
import com.yanakudrinskaya.core.utils.ResourcesProvider
import com.yanakudrinskaya.data.NetworkClient
import com.yanakudrinskaya.data.db.AppDatabase
import com.yanakudrinskaya.data.mappers.FavoriteCourseMapper
import com.yanakudrinskaya.data.network.CoursesApi
import com.yanakudrinskaya.data.network.MockInterceptor
import com.yanakudrinskaya.data.network.RetrofitNetworkClient
import com.yanakudrinskaya.data.repository.CoursesRepositoryImpl
import com.yanakudrinskaya.data.repository.FavoriteRepositoryImpl
import com.yanakudrinskaya.data.repository.ResourcesProviderImpl
import com.yanakudrinskaya.data.utils.NetworkManager
import com.yanakudrinskaya.domain.courses.api.CoursesRepository
import com.yanakudrinskaya.domain.favorite.FavoriteRepository
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {

    single<ResourcesProvider> { ResourcesProviderImpl(get()) }

    factory { Gson() }

    single { NetworkManager(androidContext()) }

    single {
        OkHttpClient.Builder()
            .addInterceptor(MockInterceptor())
            .build()
    }

    single<CoursesApi> {
        Retrofit.Builder()
            .baseUrl("https://api.example.com/")
            .client(get<OkHttpClient>())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoursesApi::class.java)
    }

    single<NetworkClient> {
        RetrofitNetworkClient(get(), get())
    }

    single<AppDatabase> {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "courses.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    factory { FavoriteCourseMapper() }

    single {
        get<AppDatabase>().favoriteCourseDao()
    }

    single<CoursesRepository> {
        CoursesRepositoryImpl(get(), get())
    }

    single<FavoriteRepository> {
        FavoriteRepositoryImpl(get(), get())
    }
}
