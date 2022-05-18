package com.otienosamwel.reads.ui.presentation.features.auth

import androidx.lifecycle.ViewModel
import com.otienosamwel.reads.ui.presentation.features.auth.signIn.LoginState
import com.otienosamwel.reads.ui.presentation.features.auth.signIn.SignUpState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor() : ViewModel() {

    val loginState = LoginState()
    val signUpState = SignUpState()


    fun signInWithEmail(email: String, password: String) {

    }
}
