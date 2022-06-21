package com.otienosamwel.reads.ui.presentation.features.auth.signIn

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class SignUpState {

    var isSignupLoading by mutableStateOf(false)

    var firstName by mutableStateOf("")
    var lastName by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var passwordConfirmation by mutableStateOf("")

    var emailHasError by mutableStateOf(false)
    var firstNameHasError by mutableStateOf(false)
    var lastNameHasError by mutableStateOf(false)
    var passwordHasError by mutableStateOf(false)
    var passwordConfirmationError by mutableStateOf(false)

    fun firstNameChanged(firstName: String) {
        this.firstName = firstName.trim()
        firstNameHasError = false
    }

    fun lastNameChanged(lastName: String) {
        this.lastName = lastName.trim()
        lastNameHasError = false
    }


    fun emailChanged(email: String) {
        emailHasError = false
        this.email = email.trim()
    }

    fun passwordChanged(password: String) {
        passwordHasError = false
        passwordConfirmationError = false
        this.password = password.trim()
    }

    fun passwordConfirmationChanged(passwordConfirmation: String) {
        passwordConfirmationError = false
        passwordHasError = false
        this.passwordConfirmation = passwordConfirmation.trim()
    }

    private fun isFirstNameValid() = firstName.isNotEmpty()

    private fun isLastNameValid() = lastName.isNotEmpty()

    private fun isEmailValid(): Boolean {
        val emailRegex = Regex("^[a-zA-Z0-9_.]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9.]+\$")
        return emailRegex.matches(email)
    }

    private fun isPasswordValid(): Boolean {
        return password.isNotEmpty()
    }

    private fun passwordsMatch(): Boolean {
        return (passwordConfirmation == password && passwordConfirmation.isNotEmpty())
    }

    fun validate(): Boolean {
        if (!isFirstNameValid()) firstNameHasError = true
        if (!isLastNameValid()) lastNameHasError = true
        if (!isEmailValid()) emailHasError = true
        if (!isPasswordValid()) passwordHasError = true
        if (!passwordsMatch()) passwordConfirmationError = true
        return (isEmailValid() && isPasswordValid() && passwordsMatch() && isFirstNameValid() && isLastNameValid())
    }

    fun clearState() {
        firstName = ""
        lastName = ""
        email = ""
        password = ""
        passwordConfirmation = ""
        emailHasError = false
        firstNameHasError = false
        lastNameHasError = false
        passwordHasError = false
        passwordConfirmationError = false
    }
}