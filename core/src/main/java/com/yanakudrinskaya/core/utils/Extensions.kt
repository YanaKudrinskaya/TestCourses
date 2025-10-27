package com.yanakudrinskaya.core.utils

import android.content.Context

fun Context.toPx(dp: Int): Int = (dp * resources.displayMetrics.density).toInt()
