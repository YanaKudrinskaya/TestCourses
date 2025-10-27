package com.yanakudrinskaya.core.utils

import android.content.Context
import com.yanakudrinskaya.core.R

fun formatPrice(price: String, context: Context): String {
    return if (price.isNotEmpty()) {
        context.getString(R.string.price_format, price)
    } else {
        ""
    }
}