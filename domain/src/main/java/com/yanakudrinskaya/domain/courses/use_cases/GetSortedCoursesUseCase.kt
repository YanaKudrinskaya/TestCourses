package com.yanakudrinskaya.domain.courses.use_cases

import com.yanakudrinskaya.domain.courses.api.CoursesRepository
import com.yanakudrinskaya.domain.models.Course
import com.yanakudrinskaya.domain.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

class GetSortedCoursesUseCase @Inject constructor(
    private val repository: CoursesRepository
) {
    operator fun invoke(): Flow<Result<List<Course>>> {
        return repository.getCourses().map { result ->
            when (result) {
                is Result.Success -> {
                    val sortedCourses = sortCoursesByPublishDate(result.data)
                    Result.Success(sortedCourses)
                }
                is Result.Error -> {
                    result
                }
            }
        }
    }

    private fun sortCoursesByPublishDate(courses: List<Course>): List<Course> {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        return courses.sortedByDescending { course ->
            dateFormat.parse(course.publishDate)
        }
    }
}
