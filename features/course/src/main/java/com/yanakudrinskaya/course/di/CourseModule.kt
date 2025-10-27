package com.yanakudrinskaya.course.di

import com.yanakudrinskaya.course.ui.view_model.CourseDetailViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val courseModule = module {

    viewModel { parameters ->
        CourseDetailViewModel(parameters.get(), get(), get()) }
}