package com.yanakudrinskaya.testcourses.navigation

import androidx.navigation.NavController
import com.yanakudrinskaya.core.navigation.NavigationContract
import com.yanakudrinskaya.core.navigation.NavigationDestination
import com.yanakudrinskaya.testcourses.R
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppNavigator @Inject constructor() : NavigationContract {

    private var navController: NavController? = null

    override fun setNavController(navController: NavController) {
        this.navController = navController
    }

    override fun navigateTo(destination: NavigationDestination) {
        when (destination) {
            is NavigationDestination.Login -> navController?.navigate(R.id.loginFragment)
            is NavigationDestination.Main -> navController?.navigate(R.id.main_nav_graph)
            is NavigationDestination.Home -> navController?.navigate(R.id.coursesFragment)
            is NavigationDestination.Favorites -> navController?.navigate(R.id.favoritesFragment)
            is NavigationDestination.Account -> navController?.navigate(R.id.accountFragment)
            is NavigationDestination.CourseDetail -> {
                navController?.navigate(R.id.courseDetailFragment)
            }
        }
    }

    override fun navigateBack() {
        navController?.navigateUp()
    }

    override fun shouldShowBottomNav(destinationId: Int): Boolean {
        return destinationId == R.id.coursesFragment ||
                destinationId == R.id.favoritesFragment ||
                destinationId == R.id.accountFragment
    }
}