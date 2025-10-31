package com.yanakudrinskaya.domain.di

import com.yanakudrinskaya.domain.auth.ValidateEmailUseCase
import com.yanakudrinskaya.domain.course.GetCourseByIdUseCase
import com.yanakudrinskaya.domain.courses.use_cases.GetCoursesUseCase
import com.yanakudrinskaya.domain.courses.use_cases.GetSortedCoursesUseCase
import com.yanakudrinskaya.domain.favorite.FavoriteInteractor
import com.yanakudrinskaya.domain.favorite.impl.FavoriteInteractorImpl
import org.koin.dsl.module

val domainModule = module {

    factory { ValidateEmailUseCase() }

    factory {
        GetCoursesUseCase(get(), get())
    }

    factory {
        GetSortedCoursesUseCase(get())
    }

    factory {
        GetCourseByIdUseCase(get())
    }

    factory<FavoriteInteractor> {
        FavoriteInteractorImpl(get())
    }
}