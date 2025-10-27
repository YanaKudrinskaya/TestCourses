package com.yanakudrinskaya.home.ui.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yanakudrinskaya.domain.courses.use_cases.GetCoursesUseCase
import com.yanakudrinskaya.domain.courses.use_cases.GetSortedCoursesUseCase
import com.yanakudrinskaya.core.models.Course
import com.yanakudrinskaya.domain.favorite.FavoriteInteractor
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

internal class HomeViewModel(
    private val getCoursesUseCase: GetCoursesUseCase,
    private val getSortedCoursesUseCase: GetSortedCoursesUseCase,
    private val favoriteInteractor: FavoriteInteractor
) : ViewModel() {

    private val coursesState = MutableStateFlow<List<Course>>(emptyList())
    fun getCoursesState(): StateFlow<List<Course>> = coursesState.asStateFlow()

    private var isSorted = false
    private var loadCoursesJob: Job? = null

    init {
        loadCourses()
    }

    private fun loadCourses() {
        loadCoursesJob?.cancel()

        loadCoursesJob = viewModelScope.launch {
            if (isSorted) {
                getSortedCoursesUseCase().collect { courses ->
                    coursesState.value = courses.toList()
                }
            } else {
                getCoursesUseCase().collect { courses ->
                    coursesState.value = courses
                }
            }
        }
    }

    fun toggleSort() {
        isSorted = !isSorted
        loadCourses()
    }

    fun toggleLike(course: Course) {
        val updatedCourses = coursesState.value.map { currentCourse ->
            if (currentCourse.id == course.id) {
                val changedCourse = currentCourse.copy(hasLike = !currentCourse.hasLike)
                viewModelScope.launch {
                    favoriteInteractor.toggleFavorite(changedCourse)
                }
                changedCourse
            } else {
                currentCourse
            }
        }
        coursesState.value = updatedCourses
    }
}