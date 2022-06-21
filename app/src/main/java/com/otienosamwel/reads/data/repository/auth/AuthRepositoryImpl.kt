package com.otienosamwel.reads.data.repository.auth

import com.otienosamwel.reads.data.remote.NetworkService
import com.otienosamwel.reads.data.remote.SignInResponse
import com.otienosamwel.reads.data.remote.SignUpResponse
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val networkService: NetworkService) :
    AuthRepository {

    override suspend fun signInUserWithEmail(email: String, password: String): SignInResponse {
        return networkService.getInitialTokenInfo(email, password)
    }

    override suspend fun signInUserWithGoogle(idToken: String) {
        networkService.getInitialTokenInfoGoogle(idToken)
    }

    override suspend fun signUpUserWithEmail(
        firstName: String,
        lastName: String,
        email: String,
        password: String
    ): SignUpResponse {
        return networkService.signUpUserWithEmail(firstName, lastName, email, password)
    }
}