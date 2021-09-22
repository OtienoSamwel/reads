package com.example.reads.data.remote.api

import com.example.reads.data.model.Book
import retrofit2.http.GET
import retrofit2.http.Query

interface Endpoints {
    @GET("/books/v1/volumes?maxResults=40&q=Harry+Potter")
    suspend fun getBooks(): Book

    @GET("/books/v1/volumes?maxResults=40")
    suspend fun getGenreBooks(@Query("q") q: String): Book
}