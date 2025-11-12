package com.yanakudrinskaya.data

import com.yanakudrinskaya.data.dto.RequestDto
import com.yanakudrinskaya.data.dto.Response

interface NetworkClient {
    suspend fun doRequest(dto: RequestDto): Response
}