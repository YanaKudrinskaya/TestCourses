package com.yanakudrinskaya.data.repository

import com.yanakudrinskaya.data.dto.CoursesResponse
import com.yanakudrinskaya.domain.courses.api.CoursesRepository
import com.yanakudrinskaya.domain.models.Course
import com.yanakudrinskaya.domain.utils.ResponseStatus
import com.yanakudrinskaya.domain.utils.Result
import com.yanakudrinskaya.data.NetworkClient
import com.yanakudrinskaya.data.dto.RequestDto
import com.yanakudrinskaya.data.mappers.CourseMapper
import com.yanakudrinskaya.domain.favorite.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoursesRepositoryImpl @Inject internal constructor(
    private val networkClient: NetworkClient,
    private val favoriteRepository: FavoriteRepository
) : CoursesRepository {

    override fun getCourses(): Flow<Result<List<Course>>> = flow {
        val response = networkClient.doRequest(RequestDto.CoursesRequest)

        if (response.status == ResponseStatus.SUCCESS && response is CoursesResponse) {
            val courses = response.courses.map { dto ->
                CourseMapper.mapToDomain(dto)
            }

            val updatedCourses = courses.map { course ->
                course.copy(hasLike = isCourseFavorite(course.id))
            }

            emit(Result.Success(updatedCourses))
        } else {
            emit(Result.Error(response.status))
        }
    }

    override suspend fun getCourseById(courseId: Long): Result<Course> {
        val response = networkClient.doRequest(RequestDto.CoursesRequest)

        return if (response.status == ResponseStatus.SUCCESS && response is CoursesResponse) {
            val courseDto = response.courses.find { dto ->
                dto.id == courseId
            }

            if (courseDto != null) {
                val course = CourseMapper.mapToDomain(courseDto)
                val isFavorite = isCourseFavorite(courseId)
                val courseWithFavorite = course.copy(hasLike = isFavorite)
                Result.Success(courseWithFavorite)
            } else {
                Result.Error(ResponseStatus.NOT_FOUND)
            }
        } else {
            Result.Error(response.status)
        }
    }

    private suspend fun isCourseFavorite(courseId: Long): Boolean {
        return favoriteRepository.isFavorite(courseId)
    }
}