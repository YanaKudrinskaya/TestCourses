package com.yanakudrinskaya.domain.courses.api

import com.yanakudrinskaya.domain.models.Course
import com.yanakudrinskaya.domain.utils.Result
import kotlinx.coroutines.flow.Flow

interface CoursesRepository {
    fun getCourses(): Flow<Result<List<Course>>>
    suspend fun getCourseById(courseId: Long): Result<Course>
}