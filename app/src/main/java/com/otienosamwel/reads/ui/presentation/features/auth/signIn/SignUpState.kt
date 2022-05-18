package com.otienosamwel.reads.ui.presentation.features.auth.signIn

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class SignUpState {

    var email by mutableStateOf("")
    var password by mutableStateOf("")
    val passwordConfirmation by mutableStateOf("")

    var emailHasError by mutableStateOf(false)
    var passwordHasError by mutableStateOf(false)
    var passwordConfirmationError by mutableStateOf(false)

    fun emailChanged(email: String) {
        emailHasError = false
        this.email = email
    }

    fun passwordChanged(password: String) {
        passwordHasError = false
        this.password = password
    }

    private fun isEmailValid(): Boolean {
        val emailRegex = Regex("^[a-zA-Z0-9_.]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9.]+\$")
        return emailRegex.matches(email)
    }

    private fun isPasswordValid(): Boolean {
        return password.isNotEmpty()
    }

    private fun passwordsMatch(): Boolean {
        return passwordConfirmation == password
    }

    fun validate(): Boolean {
        if (!isEmailValid()) emailHasError = true
        if (!isPasswordValid()) passwordHasError = true
        if (!passwordsMatch()) passwordConfirmationError = true
        return (isEmailValid() && isPasswordValid() && passwordsMatch())
    }
}