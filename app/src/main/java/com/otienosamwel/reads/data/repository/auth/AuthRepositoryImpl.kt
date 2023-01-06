package com.otienosamwel.reads.data.repository.auth

import com.otienosamwel.reads.data.remote.NetworkService
import com.otienosamwel.reads.data.remote.SignInResponse
import com.otienosamwel.reads.data.remote.SignUpResponse
import com.otienosamwel.reads.data.retrofit.ApiEndpoints
import com.otienosamwel.reads.ui.presentation.features.auth.AuthViewModel
import java.security.PrivateKey
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val networkService: NetworkService
    , private val apiEndpoints : ApiEndpoints
) :
    AuthRepository {

    override suspend fun signInUserWithEmail(data: AuthViewModel.LoginData): SignInResponse {
        return apiEndpoints.login(data)
    }

    override suspend fun signInUserWithGoogle(idToken: String): SignUpResponse {
        return networkService.getInitialTokenInfoGoogle(idToken)
    }

    override suspend fun signUpUserWithEmail(
        firstName: String,
        lastName: String,
        email: String,
        password: String
    ): SignUpResponse {
        return networkService.signUpUserWithEmail(firstName, lastName, email, password)
    }

    override suspend fun passwordReset(email: String) {
        networkService.passwordReset(email = email)
    }
}