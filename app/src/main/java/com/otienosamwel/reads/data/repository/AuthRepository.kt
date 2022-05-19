package com.otienosamwel.reads.data.repository

interface AuthRepository {
    fun signInUserWithEmail(email: String, password: String)
    fun signInUserWithGoogle(idToken: String)
    suspend fun getInitialToken()
}