package com.yanakudrinskaya.core.navigation

import androidx.navigation.NavController

interface NavigationContract {
    fun setNavController(navController: NavController)
    fun navigateTo(destination: NavigationDestination, popUpTo: NavigationDestination? = null)
    fun navigateBack()
}

sealed class NavigationDestination(val route: String) {
    object Login : NavigationDestination(LOGIN)
    object Main : NavigationDestination(MAIN)
    object Home : NavigationDestination(HOME)
    object Favorites : NavigationDestination(FAVORITES)
    object Account : NavigationDestination(ACCOUNT)

    data class CourseDetail(val courseId: Long) : NavigationDestination(COURSEDETAIL) {
        companion object {
            const val ARG_COURSE_ID = "courseId"
        }
    }

    companion object {
        const val LOGIN = "login"
        const val MAIN = "main_navigation"
        const val HOME = "coursesFragment"
        const val FAVORITES = "favoritesFragment"
        const val ACCOUNT = "accountFragment"
        const val COURSEDETAIL = "courseDetailFragment"
    }
}