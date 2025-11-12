package com.yanakudrinskaya.data.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.yanakudrinskaya.data.NetworkClient
import com.yanakudrinskaya.data.db.AppDatabase
import com.yanakudrinskaya.data.network.CoursesApi
import com.yanakudrinskaya.data.network.MockInterceptor
import com.yanakudrinskaya.data.network.RetrofitNetworkClient
import com.yanakudrinskaya.data.repository.CoursesRepositoryImpl
import com.yanakudrinskaya.data.repository.FavoriteRepositoryImpl
import com.yanakudrinskaya.data.utils.ResourcesProviderImpl
import com.yanakudrinskaya.domain.ResourcesProvider
import com.yanakudrinskaya.domain.courses.api.CoursesRepository
import com.yanakudrinskaya.domain.favorite.FavoriteRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    internal fun provideOkHttpClient(mockInterceptor: MockInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(mockInterceptor)
            .build()
    }

    @Provides
    @Singleton
    internal fun provideCoursesApi(okHttpClient: OkHttpClient): CoursesApi {
        return Retrofit.Builder()
            .baseUrl("https://api.example.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoursesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "courses.db"
        )
            .fallbackToDestructiveMigration(false)
            .build()
    }

    @Provides
    fun provideFavoriteCourseDao(appDatabase: AppDatabase) = appDatabase.favoriteCourseDao()
}

@Module
@InstallIn(SingletonComponent::class)
abstract class BindingModule {

    @Binds
    @Singleton
    internal abstract fun bindNetworkClient(impl: RetrofitNetworkClient): NetworkClient

    @Binds
    @Singleton
    internal abstract fun bindCoursesRepository(impl: CoursesRepositoryImpl): CoursesRepository

    @Binds
    @Singleton
    internal abstract fun bindFavoriteRepository(impl: FavoriteRepositoryImpl): FavoriteRepository

    @Binds
    @Singleton
    internal abstract fun bindResourcesProvider(impl: ResourcesProviderImpl): ResourcesProvider
}
