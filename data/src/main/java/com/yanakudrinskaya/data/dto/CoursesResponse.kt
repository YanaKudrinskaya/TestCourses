package com.yanakudrinskaya.data.dto

data class CoursesResponse(
    val courses: List<CourseDto>
) : Response()