package com.yanakudrinskaya.data.repository

import com.yanakudrinskaya.data.dto.CoursesResponse
import com.yanakudrinskaya.domain.courses.api.CoursesRepository
import com.yanakudrinskaya.core.models.Course
import com.yanakudrinskaya.core.utils.ResponseStatus
import com.yanakudrinskaya.data.NetworkClient
import com.yanakudrinskaya.data.dto.RequestDto
import com.yanakudrinskaya.data.mappers.CourseMapper
import com.yanakudrinskaya.domain.favorite.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class CoursesRepositoryImpl(
    private val networkClient: NetworkClient,
    private val favoriteRepository: FavoriteRepository
) : CoursesRepository {

    override fun getCourses(): Flow<List<Course>> = flow {

        val response = networkClient.doRequest(RequestDto.CoursesRequest)

        if (response.status == ResponseStatus.SUCCESS && response is CoursesResponse) {
            val courses = response.courses.map { dto ->
                CourseMapper.mapToDomain(dto)
            }
            courses.forEach { course ->
                if (course.hasLike) {
                    favoriteRepository.addToFavorite(course)
                }
            }
            emit(courses)
        } else {
            emit(emptyList())
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
                Result.success(course)
            } else {
                Result.failure(Exception("Course with id $courseId not found"))
            }
        } else {
            Result.failure(Exception("Failed to load courses: ${response.errorMessage}"))
        }
    }
}