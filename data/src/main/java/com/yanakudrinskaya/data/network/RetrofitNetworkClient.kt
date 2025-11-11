package com.yanakudrinskaya.data.network

import com.yanakudrinskaya.core.utils.ResponseStatus
import com.yanakudrinskaya.data.NetworkClient
import com.yanakudrinskaya.data.dto.CoursesResponse
import com.yanakudrinskaya.data.dto.RequestDto
import com.yanakudrinskaya.data.dto.Response
import com.yanakudrinskaya.data.utils.NetworkManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class RetrofitNetworkClient @Inject constructor(
    private val coursesApiService: CoursesApi,
    private val networkManager: NetworkManager
) : NetworkClient {

    override suspend fun doRequest(dto: RequestDto): Response {
        if (!networkManager.isConnected()) {
            return Response().apply { status = ResponseStatus.NO_INTERNET }
        }

        return when (dto) {
            is RequestDto.CoursesRequest -> {
                try {
                    val response = coursesApiService.getCourses()
                    response.apply {
                        status = ResponseStatus.SUCCESS
                    }
                } catch (e: Exception) {
                    CoursesResponse(emptyList()).apply {
                        status = ResponseStatus.SERVER_ERROR
                        errorMessage = e.message ?: "Network error occurred"
                    }
                }
            }
        }
    }
}