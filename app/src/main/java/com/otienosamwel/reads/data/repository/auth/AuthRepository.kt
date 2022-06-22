package com.otienosamwel.reads.data.repository.auth

import com.otienosamwel.reads.data.remote.SignInResponse
import com.otienosamwel.reads.data.remote.SignInWithGoogleResponse
import com.otienosamwel.reads.data.remote.SignUpResponse

interface AuthRepository {
    suspend fun signInUserWithEmail(email: String, password: String): SignInResponse

    suspend fun signInUserWithGoogle(idToken: String) : SignInWithGoogleResponse

    suspend fun signUpUserWithEmail(
        firstName: String,
        lastName: String,
        email: String,
        password: String
    ): SignUpResponse
}