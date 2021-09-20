package com.example.reads.data.repository

import com.example.reads.data.local.ReadsDao
import com.example.reads.data.model.Book
import com.example.reads.data.model.Item
import com.example.reads.data.remote.api.Endpoints
import javax.inject.Inject

class ReadsRepositoryImpl @Inject constructor(
    private val service: Endpoints,
    private val dao: ReadsDao,

    ) : ReadsRepository {
    override suspend fun getDbBooks(): List<Item> {
        return dao.getDbBooks()
    }

    override suspend fun getBooks(): Book {
        return service.getBooks()

    }
}