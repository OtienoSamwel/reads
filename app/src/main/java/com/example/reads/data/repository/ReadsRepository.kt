package com.example.reads.data.repository

import com.example.reads.data.model.Book
import com.example.reads.data.model.Item

interface ReadsRepository {
    suspend fun getDbBooks(): List<Item>
    suspend fun getBooks(): Book
}