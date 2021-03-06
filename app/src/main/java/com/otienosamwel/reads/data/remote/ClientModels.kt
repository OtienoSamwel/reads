package com.otienosamwel.reads.data.remote

import com.otienosamwel.reads.data.model.SearchResult
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TokenInfo(
    @SerialName("token") val accessToken: String?,
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
    val hasError: Boolean,
    val errorMessage: String? = null
)

data class SignInResponse(
    val hasError: Boolean,
    val errorMessage: String? = null
)

data class SignInWithGoogleResponse(
    val hasError: Boolean,
    val errorMessage: String? = null
)

data class SearchResponse(
    val hasError: Boolean,
    val errorMessage: String? = "There was an error performing that request.",
    val result: SearchResult?
)