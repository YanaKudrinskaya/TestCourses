package com.yanakudrinskaya.domain.favorite.impl

import com.yanakudrinskaya.core.models.Course
import com.yanakudrinskaya.domain.favorite.FavoriteInteractor
import com.yanakudrinskaya.domain.favorite.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteInteractorImpl @Inject constructor(
    private val repository: FavoriteRepository,
) : FavoriteInteractor {
    override suspend fun toggleFavorite(course: Course) {

        if (course.hasLike) {
            repository.addToFavorite(course)
        } else {
            repository.removeFromFavorite(course.id)
        }
    }

    override fun getFavorite(): Flow<List<Course>> {
        return repository.getFavorite()
    }
}
