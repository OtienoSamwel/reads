package com.otienosamwel.reads.ui.presentation.features.auth.signIn

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

open class LoginState {

    var isLoginLoading by mutableStateOf(false)
    var email by mutableStateOf("")
    var password by mutableStateOf("")

    var emailHasError by mutableStateOf(false)
    var passwordHasError by mutableStateOf(false)

    fun emailChanged(email: String) {
        emailHasError = false
        this.email = email.trim()
    }

    fun passwordChanged(password: String) {
        passwordHasError = false
        this.password = password.trim()
    }

    private fun isEmailValid(): Boolean {
        val emailRegex = Regex("^[a-zA-Z0-9_.]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9.]+\$")
        return emailRegex.matches(email)
    }

    private fun isPasswordValid(): Boolean {
        return password.isNotEmpty()
    }

    fun validate(): Boolean {
        if (!isEmailValid()) emailHasError = true
        if (!isPasswordValid()) passwordHasError = true
        return (isEmailValid() && isPasswordValid())
    }

    fun clearState() {
        email = ""
        password = ""
        emailHasError = false
        passwordHasError = false
    }
}