package com.yanakudrinskaya.domain

interface ResourcesProvider {
    fun getString(resId: Int): String
    fun getString(resId: Int, vararg formatArgs: Any): String
}