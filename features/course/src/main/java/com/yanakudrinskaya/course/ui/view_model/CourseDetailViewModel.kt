package com.yanakudrinskaya.course.ui.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yanakudrinskaya.domain.models.Course
import com.yanakudrinskaya.domain.utils.Result
import com.yanakudrinskaya.course.ui.models.CourseScreenState
import com.yanakudrinskaya.domain.course.GetCourseByIdUseCase
import com.yanakudrinskaya.domain.favorite.FavoriteInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@HiltViewModel
internal class CourseDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getCourseByIdUseCase: GetCourseByIdUseCase,
    private val favoriteInteractor: FavoriteInteractor
) : ViewModel() {

    private val courseId: Long get() = savedStateHandle.get<Long>("courseId") ?: -1L

    private var screenStateLiveData = MutableLiveData<CourseScreenState>()
    fun getScreenStateLiveData(): LiveData<CourseScreenState> = screenStateLiveData

    private var currentCourse: Course? = null
    private var favoriteJob: Job? = null

    init {
        loadCourseModel(courseId)
    }

    private fun loadCourseModel(courseId: Long) {
        viewModelScope.launch {
            when (val result = getCourseByIdUseCase.invoke(courseId)) {
                is Result.Success -> {
                    val course = result.data
                    currentCourse = course
                    screenStateLiveData.value = CourseScreenState.Content(course)
                }
                is Result.Error -> {
                    screenStateLiveData.value = CourseScreenState.Error(
                        message = result.message ?: "Failed to load course"
                    )
                }
            }
        }
    }

    fun onFavoriteClicked() {
        currentCourse?.let { course ->
            val updated = course.copy(hasLike = !course.hasLike)
            currentCourse = updated

            favoriteJob?.cancel()
            favoriteJob = viewModelScope.launch {
                favoriteInteractor.toggleFavorite(updated)
                screenStateLiveData.value = CourseScreenState.Content(updated)
            }
        }
    }
}