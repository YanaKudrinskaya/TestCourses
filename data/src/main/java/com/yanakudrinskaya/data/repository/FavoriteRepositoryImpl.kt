package com.yanakudrinskaya.data.repository

import com.yanakudrinskaya.core.models.Course
import com.yanakudrinskaya.domain.favorite.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FavoriteRepositoryImpl(
//    private val favoriteCourseDao: FavoriteCourseDao,
//    private val favoriteCourseMapper: FavoriteCourseMapper,
) : FavoriteRepository {

    private val _favoriteCourses = MutableStateFlow<List<Course>>(emptyList())
    override fun getFavorite(): Flow<List<Course>> = _favoriteCourses.asStateFlow()

    override suspend fun addToFavorite(course: Course) {
        val currentList = _favoriteCourses.value.toMutableList()
        if (!currentList.any { it.id == course.id }) {
            currentList.add(course)
            _favoriteCourses.value = currentList
        }
    }

    override suspend fun removeFromFavorite(courseId: Long) {
        val currentList = _favoriteCourses.value.toMutableList()
        currentList.removeAll { it.id == courseId }
        _favoriteCourses.value = currentList
    }
}