package com.yanakudrinskaya.data.dto

internal sealed interface RequestDto {
    data object CoursesRequest : RequestDto
}