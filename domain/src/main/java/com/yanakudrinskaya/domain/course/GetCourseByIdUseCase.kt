package com.yanakudrinskaya.domain.course

import com.yanakudrinskaya.domain.utils.Result
import com.yanakudrinskaya.domain.courses.api.CoursesRepository
import com.yanakudrinskaya.domain.models.Course
import javax.inject.Inject

class GetCourseByIdUseCase @Inject constructor(
    private val repository: CoursesRepository
) {
    suspend operator fun invoke(courseId: Long): Result<Course> {
        return repository.getCourseById(courseId)
    }
}