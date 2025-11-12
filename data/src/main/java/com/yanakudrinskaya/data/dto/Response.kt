package com.yanakudrinskaya.data.dto

import com.yanakudrinskaya.domain.utils.ResponseStatus

open class Response {
    var status: ResponseStatus = ResponseStatus.UNKNOWN_ERROR
    var errorMessage: String? = null
}