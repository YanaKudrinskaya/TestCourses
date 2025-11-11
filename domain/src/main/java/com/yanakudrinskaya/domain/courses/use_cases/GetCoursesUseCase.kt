package com.yanakudrinskaya.domain.courses.use_cases

import com.yanakudrinskaya.domain.courses.api.CoursesRepository
import com.yanakudrinskaya.core.models.Course
import com.yanakudrinskaya.core.utils.Result
import com.yanakudrinskaya.domain.favorite.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetCoursesUseCase @Inject constructor(
    private val repository: CoursesRepository,
    private val favoriteRepository: FavoriteRepository
) {
    operator fun invoke(): Flow<Result<List<Course>>> = combine(
        repository.getCourses(),
        favoriteRepository.getFavorite()
    ) { networkResult, favoriteCourses ->
        when (networkResult) {
            is Result.Success -> {
                val coursesWithFavorites = networkResult.data.map { course ->
                    val isFavorite = favoriteCourses.any { it.id == course.id }
                    course.copy(hasLike = isFavorite)
                }
                Result.Success(coursesWithFavorites)
            }
            is Result.Error -> {
                networkResult
            }
        }
    }
}