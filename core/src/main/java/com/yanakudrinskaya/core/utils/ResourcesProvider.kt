package com.yanakudrinskaya.core.utils

interface ResourcesProvider {
    fun getString(resId: Int): String
    fun getString(resId: Int, vararg formatArgs: Any): String
}