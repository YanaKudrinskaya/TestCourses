package com.yanakudrinskaya.data.repository

import android.content.Context
import com.yanakudrinskaya.core.utils.ResourcesProvider

class ResourcesProviderImpl(
    private val context: Context
) : ResourcesProvider {

    override fun getString(resId: Int): String {
        return context.getString(resId)
    }

    override fun getString(resId: Int, vararg formatArgs: Any): String {
        return context.getString(resId, *formatArgs)
    }
}