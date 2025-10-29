package com.yanakudrinskaya.data

import com.yanakudrinskaya.data.dto.RequestDto
import com.yanakudrinskaya.data.dto.Response

internal interface NetworkClient {
    suspend fun doRequest(dto: RequestDto): Response
}