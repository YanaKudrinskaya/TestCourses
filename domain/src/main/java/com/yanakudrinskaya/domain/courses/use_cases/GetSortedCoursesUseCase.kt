package com.yanakudrinskaya.domain.courses.use_cases

import com.yanakudrinskaya.domain.courses.api.CoursesRepository
import com.yanakudrinskaya.core.models.Course
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.text.SimpleDateFormat
import java.util.Locale

class GetSortedCoursesUseCase(
    private val repository: CoursesRepository
) {
    operator fun invoke(): Flow<List<Course>> {
        return repository.getCourses().map { courses ->
            sortCoursesByPublishDate(courses)
        }
    }

    private fun sortCoursesByPublishDate(courses: List<Course>): List<Course> {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        return courses.sortedByDescending { course ->
            dateFormat.parse(course.publishDate)
        }
    }
}
