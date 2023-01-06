package com.otienosamwel.reads.data.repository.auth

import com.otienosamwel.reads.data.remote.SignInResponse
import com.otienosamwel.reads.data.remote.SignUpResponse
import com.otienosamwel.reads.ui.presentation.features.auth.AuthViewModel

interface AuthRepository {
    suspend fun signInUserWithEmail( data : AuthViewModel.LoginData): SignInResponse

    suspend fun signInUserWithGoogle(idToken: String): SignUpResponse

    suspend fun signUpUserWithEmail(
        firstName: String,
        lastName: String,
        email: String,
        password: String
    ): SignUpResponse

    suspend fun passwordReset(email: String)
}