package com.yanakudrinskaya.core.navigation

interface NavigationContract {
    fun navigateToHome()
    fun navigateToCourseDetail(courseId: Long)
}