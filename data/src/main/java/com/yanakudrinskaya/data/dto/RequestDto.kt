package com.yanakudrinskaya.data.dto

sealed interface RequestDto {
    data object CoursesRequest : RequestDto
}