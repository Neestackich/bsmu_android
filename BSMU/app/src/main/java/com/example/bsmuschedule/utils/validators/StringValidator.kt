package com.example.bsmuschedule.utils.validators

import java.util.regex.Pattern

object StringValidator {

    private val EMAIL_ADDRESS_PATTERN: Pattern = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )

    private val PASSWORD_PATTERN: Pattern = Pattern.compile(
        "^(?=.*[@#$%^&+=])(?=\\S+$).{4,}$"
    )

    fun isEmailValid(email: String): Boolean {
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches()
    }

    fun isPasswordValid(password: String): Boolean {
        return PASSWORD_PATTERN.matcher(password).matches()
    }

}