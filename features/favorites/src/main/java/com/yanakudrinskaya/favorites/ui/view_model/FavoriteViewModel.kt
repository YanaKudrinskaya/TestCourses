package com.yanakudrinskaya.favorites.ui.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yanakudrinskaya.domain.models.Course
import com.yanakudrinskaya.domain.favorite.FavoriteInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
internal class FavoriteViewModel @Inject constructor(
    private val favoriteInteractor: FavoriteInteractor
) : ViewModel() {

    private val coursesState = MutableStateFlow<List<Course>>(emptyList())
    fun getCoursesState(): StateFlow<List<Course>> = coursesState.asStateFlow()

    private var loadCoursesJob: Job? = null

    init {
        loadFavorites()
    }

    private fun loadFavorites() {
        loadCoursesJob?.cancel()

        loadCoursesJob = viewModelScope.launch {
            favoriteInteractor.getFavorite().collect { courses ->
                coursesState.value = courses.toList()
            }
        }
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