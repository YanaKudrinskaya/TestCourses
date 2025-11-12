package com.yanakudrinskaya.domain.favorite

import com.yanakudrinskaya.domain.models.Course
import kotlinx.coroutines.flow.Flow

interface FavoriteInteractor {
    suspend fun toggleFavorite(course: Course)
    fun getFavorite(): Flow<List<Course>>
}