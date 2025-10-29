package com.yanakudrinskaya.data.network

import com.yanakudrinskaya.data.dto.CoursesResponse
import retrofit2.http.GET

internal interface CoursesApi {
    @GET("courses")
    suspend fun getCourses(): CoursesResponse
}