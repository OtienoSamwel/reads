package com.otienosamwel.reads.data.repository.auth

import com.otienosamwel.reads.data.remote.SignInResponse
import com.otienosamwel.reads.data.remote.SignUpResponse

interface AuthRepository {
    suspend fun signInUserWithEmail(email: String, password: String): SignInResponse

    suspend fun signInUserWithGoogle(idToken: String)

    suspend fun signUpUserWithEmail(
        firstName: String,
        lastName: String,
        email: String,
        password: String
    ): SignUpResponse
}