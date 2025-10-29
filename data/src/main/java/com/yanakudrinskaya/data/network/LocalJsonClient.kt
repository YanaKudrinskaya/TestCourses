// LocalJsonClient.kt
package com.yanakudrinskaya.data.network

import com.google.gson.Gson
import com.yanakudrinskaya.data.NetworkClient
import com.yanakudrinskaya.data.dto.CoursesResponse
import com.yanakudrinskaya.data.dto.RequestDto
import com.yanakudrinskaya.data.dto.Response
import com.yanakudrinskaya.core.utils.ResponseStatus

internal class LocalJsonClient : NetworkClient {

    override suspend fun doRequest(dto: RequestDto): Response {
        return when (dto) {
            is RequestDto.CoursesRequest -> {
                try {
                    val jsonString = loadJsonFromAssets()
                    val coursesResponse = Gson().fromJson(jsonString, CoursesResponse::class.java)
                    coursesResponse.apply {
                        status = ResponseStatus.SUCCESS
                    }
                } catch (e: Exception) {
                    CoursesResponse(emptyList()).apply {
                        status = ResponseStatus.UNKNOWN_ERROR
                        errorMessage = e.message
                    }
                }
            }
        }
    }

    private fun loadJsonFromAssets(): String {
        return try {
            val inputStream = javaClass.classLoader?.getResourceAsStream("assets/data/courses.json")
            inputStream?.bufferedReader().use { it?.readText() } ?: "{\"courses\":[]}"
        } catch (e: Exception) {
            "{\"courses\":[]}"
        }
    }
}