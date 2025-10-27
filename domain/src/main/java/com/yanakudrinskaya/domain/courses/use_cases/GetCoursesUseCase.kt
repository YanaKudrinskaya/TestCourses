package com.yanakudrinskaya.domain.courses.use_cases

import com.yanakudrinskaya.domain.courses.api.CoursesRepository
import com.yanakudrinskaya.core.models.Course
import com.yanakudrinskaya.domain.favorite.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class GetCoursesUseCase(
    private val repository: CoursesRepository,
    private val favoriteRepository: FavoriteRepository
) {
    operator fun invoke(): Flow<List<Course>> = combine(
        repository.getCourses(),
        favoriteRepository.getFavorite()
    ) { networkCourses, favoriteCourses ->
        networkCourses.map { course ->
            val isFavorite = favoriteCourses.any { it.id == course.id }
            course.copy(hasLike = isFavorite)
        }
    }
}