package com.otienosamwel.reads.ui.presentation.features.auth

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.otienosamwel.reads.data.repository.auth.AuthRepository
import com.otienosamwel.reads.ui.presentation.features.auth.signIn.LoginState
import com.otienosamwel.reads.ui.presentation.features.auth.signIn.SignUpState
import com.otienosamwel.reads.ui.presentation.features.main.MainActivity
import com.otienosamwel.reads.utils.toast
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {

    val loginState = LoginState()
    val signUpState = SignUpState()


    fun signInWithEmail(email: String, password: String, activity: AuthActivity) {
        viewModelScope.launch {
            loginState.isLoginLoading = true

            val response =
                withContext(Dispatchers.IO) { authRepository.signInUserWithEmail(email, password) }

            loginState.isLoginLoading = false

            if (!response.hasError) {
                loginState.clearState()

                //start activity on main thread
                withContext(Dispatchers.Main) {
                    activity.startActivity(Intent(activity, MainActivity::class.java))
                    activity.finish()
                }

            } else {
                activity.toast("There was an error signing in. Please try again later")
            }

        }
    }

    fun singInWithGoogle(googleIdToken: String) {
        viewModelScope.launch {
            authRepository.signInUserWithGoogle(googleIdToken)
        }
    }

    fun signUp(context: Context, navigateToSignInScreen: () -> Unit) {
        viewModelScope.launch {

            signUpState.isSignupLoading = true

            val response = withContext(Dispatchers.IO) {
                authRepository.signUpUserWithEmail(
                    signUpState.firstName,
                    signUpState.lastName,
                    signUpState.email,
                    signUpState.password
                )
            }
            signUpState.isSignupLoading = false

            if (!response.hasError) {
                context.toast("Sign up successful. Go ahead and log in to your account.")
                signUpState.clearState()
                navigateToSignInScreen()
            } else {
                context.toast("There was a problem signing up. Please try again.")
            }
        }
    }
}
