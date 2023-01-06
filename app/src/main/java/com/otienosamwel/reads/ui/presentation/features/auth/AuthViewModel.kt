package com.otienosamwel.reads.ui.presentation.features.auth

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.otienosamwel.reads.domain.model.Resource
import com.otienosamwel.reads.domain.useCases.LoginUseCase
import com.otienosamwel.reads.domain.useCases.SignInWithGoogleUseCase
import com.otienosamwel.reads.domain.useCases.SignUpUseCase
import com.otienosamwel.reads.ui.presentation.features.main.MainActivity
import com.otienosamwel.reads.utils.toast
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val singInWithGoogleUseCase: SignInWithGoogleUseCase,
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    val loginState = AuthState.loginState
    val signUpState = AuthState.singUpState
    val passwordResetState = AuthState.passwordResetState
    val isLoading = mutableStateOf(false)


    fun signInWithEmail(activity: AuthActivity) {

        if (!loginState.validate()) {
            return
        }

        val data = loginState.getData(LoginData::class)

        viewModelScope.launch {
            loginUseCase(email = data.email, password = data.password).collect { resource ->
                when (resource) {
                    is Resource.Loading -> isLoading.value = true
                    is Resource.Error -> {
                        isLoading.value = false
                        activity.toast("Could not sign you in at the moment. Please try again later ")
                    }
                    is Resource.Success -> {
                        activity.startActivity(Intent(activity, MainActivity::class.java))
                    }
                }
            }
        }
    }

    fun singInWithGoogle(googleIdToken: String, activity: AuthActivity) {
        viewModelScope.launch {
            singInWithGoogleUseCase(googleIdToken).collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        isLoading.value = true
                    }
                    is Resource.Success -> {
                        isLoading.value = false
                        activity.startActivity(Intent(activity, MainActivity::class.java))
                    }
                    is Resource.Error -> {
                        isLoading.value = false
                        activity.toast(resource.message.toString())
                    }
                }
            }
        }
    }

    fun signUp(context: Context, navigateToSignInScreen: () -> Unit) {
        if (!signUpState.validate()) {
            return
        }
        val data = signUpState.getData(SignUpData::class)

        viewModelScope.launch {
            signUpUseCase(
                firstName = data.firstName,
                lastName = data.lastName,
                email = data.email,
                password = data.password
            ).collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        isLoading.value = false
                    }
                    is Resource.Success -> {
                        isLoading.value = false
                        context.toast("Sign up successful. Go ahead and log in to your account.")
                        navigateToSignInScreen()
                    }
                    is Resource.Error -> {
                        isLoading.value = false
                        context.toast("There was a problem signing up. Please try again.")
                    }
                }
            }
        }
    }

    fun resetPassword() {
        if (!passwordResetState.validate()) {
            return
        }

        val data  = passwordResetState.getData(PasswordResetData::class)

        viewModelScope.launch {

        }
    }

    data class SignUpData(
        val firstName: String, val lastName: String, val email: String, val password: String
    )

    data class LoginData(
        val email: String, val password: String
    )

    data class PasswordResetData(val email: String)
}
