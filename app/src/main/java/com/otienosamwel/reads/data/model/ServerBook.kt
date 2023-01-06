package com.otienosamwel.reads.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "server_book")
class ServerBook(
    @PrimaryKey
    val id: Int,
    val title: String,
    val author: Author,
    val publicationDate: String,
    val snippet: String,
    val summary: String,
    val imageUri: String,
    val pageCount: Int,
)