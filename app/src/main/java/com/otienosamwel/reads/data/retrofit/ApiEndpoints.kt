package com.otienosamwel.reads.data.retrofit

import com.otienosamwel.reads.data.remote.SignInResponse
import com.otienosamwel.reads.ui.presentation.features.auth.AuthViewModel
import retrofit2.http.Body
import retrofit2.http.POST


interface ApiEndpoints {

    @POST("/api-auth/login/")
    suspend fun login(@Body loginModel: AuthViewModel.LoginData): SignInResponse
}