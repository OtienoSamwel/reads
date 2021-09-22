package com.example.reads.data.repository

import com.example.reads.data.local.ReadsDao
import com.example.reads.data.model.Book
import com.example.reads.data.model.Item
import com.example.reads.data.remote.api.Endpoints
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadsRepositoryImpl @Inject constructor(
    private val service: Endpoints,
    private val dao: ReadsDao,
) : ReadsRepository {

    override val books: Flow<List<Item>> = dao.getDbBooks()

    override suspend fun getBooks() {
        val book = service.getBooks()
        dao.insertBooks(book.items)
    }

    override suspend fun getGenreBooks(genre: String): Book {
        return service.getGenreBooks(genre)
    }
}