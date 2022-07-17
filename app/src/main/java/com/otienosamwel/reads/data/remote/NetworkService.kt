package com.otienosamwel.reads.data.remote

import android.content.Context
import com.otienosamwel.reads.R
import com.otienosamwel.reads.data.model.SearchResult
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import javax.inject.Inject


class NetworkService @Inject constructor(
    private val preferences: Preferences, @ApplicationContext private val context: Context
) {
    private val baseUrl = context.getString(R.string.base_url)

    private val googleBooksBaseUrl = "https://www.googleapis.com/books/v1/volumes"


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
            }
        }
    }

    /**
     * function to login the user and save the access and refresh tokens
     * @param email the user's registered email
     * @param password the user's password
     */
    suspend fun getInitialTokenInfo(email: String, password: String): SignInResponse =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val httpResponse = tokenClient.post("$baseUrl/user/login") {
                    setBody(
                        TokenRequest(
                            email = email,
                            password = password,
                        )
                    )
                }

                when (httpResponse.status) {
                    HttpStatusCode.OK -> {
                        httpResponse.body<TokenInfo>().also { tokenInfo ->
                            preferences.setToken(tokenInfo.accessToken!!)

                        }
                        SignInResponse(hasError = false)
                    }
                    HttpStatusCode.Unauthorized -> {
                        SignInResponse(
                            hasError = true,
                            errorMessage = "Incorrect credentials provided."
                        )
                    }
                    else -> {
                        throw Exception(httpResponse.toString())
                    }
                }

            } catch (e: Exception) {
                e.printStackTrace()
                SignInResponse(hasError = true)
            }
        }

    suspend fun getInitialTokenInfoGoogle(googleIdToken: String): SignInWithGoogleResponse =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val httpResponse = tokenClient.post("$baseUrl/user/google_sign_in") {
                    setBody(
                        TokenRequestGoogle(idToken = googleIdToken)
                    )
                }

                if (httpResponse.status == HttpStatusCode.OK) {
                    httpResponse.body<TokenInfo>().also { tokenInfo ->
                        preferences.setToken(tokenInfo.accessToken!!)
                    }
                    SignInWithGoogleResponse(hasError = false)
                } else {
                    throw Exception(httpResponse.toString())
                }

            } catch (e: Exception) {
                SignInWithGoogleResponse(hasError = true)
            }
        }


    suspend fun signUpUserWithEmail(
        firstName: String, lastname: String, email: String, password: String
    ): SignUpResponse = withContext(Dispatchers.IO) {
        return@withContext try {
            val httpResponse = client.post("$baseUrl/user/signup") {
                setBody(
                    SignUpRequest(
                        firstName = firstName,
                        lastName = lastname,
                        email = email,
                        password = password,
                    )
                )
            }
            if (httpResponse.status == HttpStatusCode.Created) {
                SignUpResponse(hasError = false)
            } else {
                throw Exception(httpResponse.status.toString())
            }
        } catch (e: Exception) {
            SignUpResponse(hasError = true)
        }
    }


    suspend fun searchBooks(query: String, startIndex: Int = 0): SearchResponse =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val httpResponse = client.get(googleBooksBaseUrl) {
                    parameter("q", query)
                    parameter("maxResults", 40)
                    parameter("startIndex", startIndex)
                }

                if (httpResponse.status == HttpStatusCode.OK) {
                    val result = httpResponse.body<SearchResult>()

                    SearchResponse(false, null, result)
                } else {
                    throw Exception(httpResponse.status.toString())
                }

            } catch (e: Exception) {
                e.printStackTrace()
                SearchResponse(true, errorMessage = e.message, null)
            }
        }
}