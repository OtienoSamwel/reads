package com.otienosamwel.reads.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "author")
data class Author(
    @PrimaryKey
    val id: Int,
    val name: String,
    val bio: String,
    val imageUri: String
)
