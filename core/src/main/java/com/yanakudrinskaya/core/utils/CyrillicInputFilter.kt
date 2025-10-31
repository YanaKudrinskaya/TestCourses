package com.yanakudrinskaya.core.utils

import android.text.InputFilter
import android.text.Spanned

class CyrillicInputFilter : InputFilter {
    override fun filter(
        p0: CharSequence?,
        p1: Int,
        p2: Int,
        p3: Spanned?,
        p4: Int,
        p5: Int
    ): CharSequence? {
        if (p0 == null) return null
        for (i in p1 until p2) {
            val char = p0[i]
            if (char.isCyrillic()) {
                return ""
            }
        }
        return null
    }

    private fun Char.isCyrillic(): Boolean {
        return this in 'а'..'я' ||
                this in 'А'..'Я' ||
                this == 'ё' ||
                this == 'Ё'
    }
}