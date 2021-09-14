package com.example.reads.data.model

import androidx.room.Embedded
import androidx.room.Entity

@Entity
data class Book(
    @Embedded val items: List<Item>,
    val kind: String,
    val totalItems: Int
)