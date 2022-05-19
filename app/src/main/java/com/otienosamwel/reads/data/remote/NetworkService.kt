package com.otienosamwel.reads.data.remote

import android.content.Context
import com.otienosamwel.reads.R
import com.otienosamwel.reads.utils.Preferences
import dagger.hilt.android.qualifiers.ApplicationContext
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import javax.inject.Inject


class NetworkService @Inject constructor(
    private val preferences: Preferences,
    @ApplicationContext private val context: Context
) {
    private val tokenUri = "" //todo
    private val baseUrl = context.getString(R.string.base_url)


    /**
     * This client is only used for authentication requests
     */
    @OptIn(ExperimentalSerializationApi::class)
    private val tokenClient = HttpClient(CIO) {

        expectSuccess = false

        defaultRequest {
            contentType(ContentType.Application.Json)
        }

        install(ContentNegotiation) {
            json(Json {
                isLenient = true
                prettyPrint = true
                ignoreUnknownKeys = true
                explicitNulls = false
            })
        }

        install(Logging) {
            level = LogLevel.ALL
            logger = Logger.DEFAULT
        }
    }

    /**
     * This client is used for all other requests
     */
    @OptIn(ExperimentalSerializationApi::class)
    private val client = HttpClient(CIO) {
        expectSuccess = false

        defaultRequest {
            contentType(ContentType.Application.Json)
        }

        install(ContentNegotiation) {
            json(Json {
                isLenient = true
                prettyPrint = true
                ignoreUnknownKeys = true
                explicitNulls = false
            })
        }

        install(Logging) {
            level = LogLevel.ALL
            logger = Logger.ANDROID
        }

        install(Auth) {
            bearer {
                loadTokens {
                    BearerTokens(preferences.getToken()!!, preferences.getRefreshToken()!!)
                }

                refreshTokens {
                    getRefreshTokenInfo()
                    BearerTokens(preferences.getToken()!!, preferences.getRefreshToken()!!)
                }
            }
        }
    }

    /**
     * makes the refresh token request and saves the new tokens to the shared preferences
     */
    private suspend fun RefreshTokensParams.getRefreshTokenInfo(): TokenInfo {
        return tokenClient.post(tokenUri) {
            setBody(
                RefreshTokenRequest(refreshToken = preferences.getRefreshToken()!!)
            )
            markAsRefreshTokenRequest()
        }.body<TokenInfo>().also { tokenInfo ->
            tokenInfo.accessToken?.let { accessToken -> preferences.setToken(token = accessToken) }
            tokenInfo.refreshToken?.let { refreshToken -> preferences.setRefreshToken(token = refreshToken) }
        }
    }

    /**
     * function to login the user and save the access and refresh tokens
     * @param email the user's registered email
     * @param password the user's password
     */
    suspend fun getInitialTokenInfo(email: String, password: String) {
        tokenClient.post(tokenUri) {
            setBody(
                AccessTokenRequest(
                    email = email,
                    password = password,
                )
            )
        }.body<TokenInfo>().also { tokenInfo ->
            tokenInfo.accessToken?.let { accessToken -> preferences.setToken(token = accessToken) }
            tokenInfo.refreshToken?.let { refreshToken -> preferences.setRefreshToken(token = refreshToken) }
        }
    }


    /**
     * kills the client when the apps on destroy is called
     */
    fun closeClient() = client.close()

}