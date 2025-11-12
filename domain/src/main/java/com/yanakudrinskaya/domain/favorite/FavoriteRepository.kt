package com.yanakudrinskaya.domain.favorite

import com.yanakudrinskaya.domain.models.Course
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    suspend fun addToFavorite(course: Course)
    suspend fun removeFromFavorite(courseId: Long)
    fun getFavorite(): Flow<List<Course>>
    suspend fun isFavorite(courseId: Long): Boolean
}