package com.example.reads.data.repository

import com.example.reads.data.model.Book
import com.example.reads.data.model.Item
import kotlinx.coroutines.flow.Flow

interface ReadsRepository {
    val books: Flow<List<Item>>
    suspend fun getBooks()
    suspend fun getGenreBooks(genre: String): Book
}