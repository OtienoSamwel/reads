package com.otienosamwel.reads.data.remote

import android.content.Context
import android.net.Uri
import com.otienosamwel.reads.R
import com.otienosamwel.reads.data.model.SearchResult
import com.otienosamwel.reads.utils.Preferences
import dagger.hilt.android.qualifiers.ApplicationContext
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.android.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.gson.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NetworkService @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val baseUrl = context.getString(R.string.base_url)
    private val booksUrlApiKey = context.getString(R.string.api_key)

    private val googleBooksBaseUrl = "https://www.googleapis.com/books/v1/volumes"


    /**
     * This client is only used for authentication requests
     */
    private val tokenClient = HttpClient(Android) {
        expectSuccess = false

        defaultRequest {
            contentType(ContentType.Application.Json)
        }

        install(ContentNegotiation) {
            gson {
                setLenient()
                setPrettyPrinting()
                serializeNulls()
            }
        }

        install(Logging) {
            level = LogLevel.BODY
            logger = Logger.DEFAULT
        }
    }

    /**
     * This client is used for all other requests
     */
    private val client = HttpClient(CIO) {
        expectSuccess = false

        defaultRequest {
            contentType(ContentType.Application.Json)
        }

        install(ContentNegotiation) {
            gson {
                setPrettyPrinting()
                setLenient()
                serializeNulls()
            }
        }

        install(Logging) {
            level = LogLevel.BODY
            logger = Logger.DEFAULT
        }
    }

    /**
     * function to login the user and save the access and refresh tokens
     * @param email the user's registered email
     * @param password the user's password
     */
    suspend fun login(email: String, password: String): SignInResponse {
        return tokenClient.post("$baseUrl/api-auth/login") {
            setBody(
                TokenRequest(
                    email = email,
                    password = password,
                )
            )
        }.body()
    }


    suspend fun getInitialTokenInfoGoogle(googleIdToken: String): SignUpResponse =
        withContext(Dispatchers.IO) {
            return@withContext tokenClient.post("$baseUrl/users/google") {
                setBody(TokenRequestGoogle(idToken = googleIdToken))
            }.body()
        }


    suspend fun signUpUserWithEmail(
        firstName: String, lastname: String, email: String, password: String
    ): SignUpResponse = withContext(Dispatchers.IO) {
        return@withContext client.post("$baseUrl/api-auth/registration") {
            setBody(
                SignUpRequest(
                    firstName = firstName,
                    lastName = lastname,
                    email = email,
                    password1 = password,
                    password2 = password,
                    username = email
                )
            )
        }.body()
    }


    suspend fun searchBooks(query: String, startIndex: Int = 0): SearchResponse =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val httpResponse = client.get(googleBooksBaseUrl) {
                    parameter("q", query)
                    parameter("maxResults", 40)
                    parameter("startIndex", startIndex)
                    parameter("orderBy", "relevance")
                    parameter("printType", "books")
                    //  parameter("key", booksUrlApiKey)
                }

                if (httpResponse.status == HttpStatusCode.OK) {
                    val result = httpResponse.body<SearchResult>()


                    //make all image links https
                    result.items?.forEach { item ->
                        item.volumeInfo?.imageLinks?.thumbnail?.let {
                            item.volumeInfo.imageLinks.thumbnail =
                                Uri.parse(it).buildUpon().scheme("https").build().toString()
                        }
                    }

                    SearchResponse(false, null, result)
                } else {
                    throw Exception(httpResponse.status.toString())
                }

            } catch (e: Exception) {
                e.printStackTrace()
                SearchResponse(true, errorMessage = e.message, null)
            }
        }

    suspend fun getBooksByGenre(genre: String, startIndex: Int = 0): GenreSearchResponse =
        withContext(Dispatchers.IO) {

            val query = "subject:\"$genre\""

            return@withContext try {
                val httpResponse = client.get(googleBooksBaseUrl) {
                    parameter("q", query)
                    parameter("maxResults", 20)
                    parameter("startIndex", startIndex)
                    parameter("orderBy", "relevance")
                    parameter("printType", "books")
                    //  parameter("key", booksUrlApiKey)
                }

                if (httpResponse.status == HttpStatusCode.OK) {
                    val result = httpResponse.body<SearchResult>()

                    //make all image links https
                    result.items?.forEach { item ->
                        item.volumeInfo?.imageLinks?.thumbnail?.let {
                            item.volumeInfo.imageLinks.thumbnail =
                                Uri.parse(it).buildUpon().scheme("https").build().toString()
                        }
                    }

                    GenreSearchResponse(hasError = false, result = result)
                } else {
                    throw Exception(httpResponse.status.toString())
                }

            } catch (e: Exception) {
                e.printStackTrace()
                GenreSearchResponse(hasError = true, errorMessage = e.message, result = null)
            }
        }


    suspend fun passwordReset(email: String) {
        client.post("$baseUrl/api-auth/password/reset") {
            setBody(
                PasswordResetBody(
                    email = email
                )
            )
        }
    }

    companion object {
        private const val TAG = "NETWORK_SERVICE"
    }
}