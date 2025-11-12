package com.yanakudrinskaya.core.navigation

import androidx.navigation.NavController

interface NavigationContract {
    fun setNavController(navController: NavController)
    fun navigateTo(destination: NavigationDestination)
    fun navigateBack()
    fun shouldShowBottomNav(destinationId: Int): Boolean
}

sealed class NavigationDestination() {
    object Login : NavigationDestination()
    object Main : NavigationDestination()
    object Home : NavigationDestination()
    object Favorites : NavigationDestination()
    object Account : NavigationDestination()
    data class CourseDetail(val courseId: Long) : NavigationDestination()
}