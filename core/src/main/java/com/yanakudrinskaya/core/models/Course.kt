package com.yanakudrinskaya.core.models

data class Course(
    val id: Long,
    val title: String,
    val text: String,
    val price: String,
    val rate: String,
    val startDate: String,
    val hasLike: Boolean,
    val publishDate: String
)