package com.yanakudrinskaya.course.ui.models

import com.yanakudrinskaya.domain.models.Course

internal sealed class CourseScreenState {
    data class Content(
        val course: Course
    ) : CourseScreenState()

    data class Error(
        val message: String
    ) : CourseScreenState()
}