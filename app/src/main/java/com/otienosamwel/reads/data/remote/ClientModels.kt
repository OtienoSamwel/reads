package com.otienosamwel.reads.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TokenInfo(
    @SerialName("access_token") val accessToken: String?,
    @SerialName("expires_in") val expiresIn: Int?,
    @SerialName("refresh_token") val refreshToken: String? = null,
)

@Serializable
data class AccessTokenRequest(
    @SerialName("email") val email: String,
    @SerialName("password") val password: String,
)

@Serializable
data class RefreshTokenRequest(
    @SerialName("refresh_token") val refreshToken: String,
)