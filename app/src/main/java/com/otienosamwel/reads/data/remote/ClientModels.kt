package com.otienosamwel.reads.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TokenInfo(
    @SerialName("access_token") val accessToken: String?,
    @SerialName("expires") val expiresIn: Int?,
    @SerialName("refresh_token") val refreshToken: String? = null,
)

@Serializable
data class TokenRequest(
    @SerialName("email") val email: String,
    @SerialName("password") val password: String,
)

@Serializable
data class TokenRequestGoogle(
    @SerialName("id_token") val idToken: String,
)

@Serializable
data class SignUpRequest(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
)

data class SignUpResponse(
    val hasError: Boolean
)

data class SignInResponse(
    val hasError: Boolean
)