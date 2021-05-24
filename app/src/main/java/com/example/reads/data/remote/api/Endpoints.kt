package com.example.reads.data.remote.api

import com.example.reads.data.remote.model.Book
import retrofit2.http.GET

interface Endpoints {
    @GET("/books/v1/volumes?maxResults=40&q=Jupiter's+Legacy")
    suspend fun getBooks(): Book
}