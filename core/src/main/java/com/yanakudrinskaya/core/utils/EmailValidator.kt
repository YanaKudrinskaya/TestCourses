package com.yanakudrinskaya.core.utils

object EmailValidator {
    private const val EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\$"

    fun isValid(email: String): Boolean {
        return email.matches(EMAIL_REGEX.toRegex())
    }
}