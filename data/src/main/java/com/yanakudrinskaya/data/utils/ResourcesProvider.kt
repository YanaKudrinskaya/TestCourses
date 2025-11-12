package com.yanakudrinskaya.data.utils

import android.content.Context
import androidx.annotation.StringRes
import com.yanakudrinskaya.domain.ResourcesProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResourcesProviderImpl @Inject internal constructor(
    @ApplicationContext private val context: Context
): ResourcesProvider {

    override fun getString(@StringRes resId: Int): String {
        return context.getString(resId)
    }

    override fun getString(@StringRes resId: Int, vararg formatArgs: Any): String {
        return context.getString(resId, *formatArgs)
    }
}