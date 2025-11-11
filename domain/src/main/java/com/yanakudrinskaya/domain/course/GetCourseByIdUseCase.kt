package com.yanakudrinskaya.domain.course

import com.yanakudrinskaya.core.models.Course
import com.yanakudrinskaya.core.utils.Result
import com.yanakudrinskaya.domain.courses.api.CoursesRepository
import javax.inject.Inject

class GetCourseByIdUseCase @Inject constructor(
    private val repository: CoursesRepository
) {
    suspend operator fun invoke(courseId: Long): Result<Course> {
        return repository.getCourseById(courseId)
    }
}