package com.otienosamwel.reads.data.remote

import com.google.gson.annotations.SerializedName
import com.otienosamwel.reads.data.model.SearchResult
import com.otienosamwel.reads.data.model.User


data class TokenInfo(
    @SerializedName("token") val accessToken: String?,
    @SerializedName("expires") val expiresIn: Int?,
    @SerializedName("refresh_token") val refreshToken: String? = null,
)

data class TokenRequest(
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String
)

data class TokenRequestGoogle(
    @SerializedName("id_token") val idToken: String,
)

data class SignUpRequest(
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    val email: String,
    val password1: String,
    val password2: String,
    val username: String
)

data class SignUpResponse(
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("refresh_token") val refreshToken: String,
    val user: User
)

data class SignInResponse(
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("refresh_token") val refreshToken: String,
    val user: User
)

data class PasswordResetResponse(
    val test  : String
)


data class SearchResponse(
    val hasError: Boolean,
    val errorMessage: String? = "There was an error performing that request.",
    val result: SearchResult?
)

data class GenreSearchResponse(
    val hasError: Boolean,
    val errorMessage: String? = "A network error occurred",
    val result: SearchResult?
)

data class PasswordResetBody(
    val email: String
)