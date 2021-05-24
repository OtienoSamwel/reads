package com.example.reads.data.remote.api

import com.example.reads.data.remote.model.Book
import retrofit2.http.GET

interface Endpoints {
    @GET("/books/v1/volumes?q=harry")
    suspend fun getBooks(): Book
}