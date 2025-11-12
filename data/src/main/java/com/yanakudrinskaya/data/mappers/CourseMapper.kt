package com.yanakudrinskaya.data.mappers

import com.yanakudrinskaya.domain.models.Course
import com.yanakudrinskaya.data.dto.CourseDto

internal object CourseMapper {
    fun mapToDomain(dto: CourseDto): Course {
        return Course(
            id = dto.id,
            title = dto.title,
            text = dto.text,
            price = dto.price,
            rate = dto.rate,
            startDate = dto.startDate,
            hasLike = dto.hasLike,
            publishDate = dto.publishDate,
        )
    }
}