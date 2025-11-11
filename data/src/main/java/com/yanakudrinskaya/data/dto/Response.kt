package com.yanakudrinskaya.data.dto

import com.yanakudrinskaya.core.utils.ResponseStatus

open class Response {
    var status: ResponseStatus = ResponseStatus.UNKNOWN_ERROR
    var errorMessage: String? = null
}