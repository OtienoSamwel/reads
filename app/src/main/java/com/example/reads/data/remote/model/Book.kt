package com.example.reads.data.remote.model

data class Book(
    val items: List<Item>,
    val kind: String,
    val totalItems: Int
)