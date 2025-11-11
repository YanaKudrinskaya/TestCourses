package com.yanakudrinskaya.data.mappers

import com.yanakudrinskaya.core.models.Course
import com.yanakudrinskaya.data.db.entity.FavoriteCourseEntity
import javax.inject.Inject

class FavoriteCourseMapper @Inject constructor() {

    fun map(course: Course): FavoriteCourseEntity {
        return FavoriteCourseEntity(
            id = course.id,
            title = course.title,
            text = course.text,
            price = course.price,
            rate = course.rate,
            startDate = course.startDate,
            hasLike = course.hasLike,
            publishDate = course.publishDate,
        )
    }

    fun map(course: FavoriteCourseEntity): Course {
        return Course(
            id = course.id,
            title = course.title,
            text = course.text,
            price = course.price,
            rate = course.rate,
            startDate = course.startDate,
            hasLike = course.hasLike,
            publishDate = course.publishDate,
        )
    }
}