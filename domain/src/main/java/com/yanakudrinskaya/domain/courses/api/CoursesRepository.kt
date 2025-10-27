package com.yanakudrinskaya.domain.courses.api

import com.yanakudrinskaya.core.models.Course
import kotlinx.coroutines.flow.Flow

interface CoursesRepository {
    fun getCourses(): Flow<List<Course>>
    suspend fun getCourseById(courseId: Long): Result<Course>
}