package com.yanakudrinskaya.data.dto

internal data class CoursesResponse(
    val courses: List<CourseDto>
) : Response()