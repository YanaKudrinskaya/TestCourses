package com.yanakudrinskaya.core.navigation

import android.util.Log
import androidx.navigation.NavController
import javax.inject.Inject

    class AppNavigatorImpl @Inject constructor() : NavigationContract {

        private var navController: NavController? = null

        override fun setNavController(navController: NavController) {
            this.navController = navController
        }

        override fun navigateTo(destination: NavigationDestination, popUpTo: NavigationDestination?) {
            try {
                val currentNavController = navController ?: run {
                    Log.e("Navigation", "NavController is not set")
                    return
                }

                val route = when (destination) {
                    is NavigationDestination.CourseDetail ->
                        "${destination.route}?${NavigationDestination.CourseDetail.ARG_COURSE_ID}=${destination.courseId}"

                    else -> destination.route
                }

//                if (popUpTo != null) {
//                    currentNavController.navigate(route) {
//                        popUpTo(popUpTo.getDestinationId()) {
//                            inclusive = true
//                        }
//                    }
//                } else {
                    currentNavController.navigate(route)
//                }
            } catch (e: Exception) {
                Log.e("Navigation", "Navigation failed for destination: $destination", e)
            }
        }

        override fun navigateBack() {
            navController?.navigateUp()
        }
    }
