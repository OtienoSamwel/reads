package com.otienosamwel.reads.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "search_item")
data class SearchItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val time: Long,
    @ColumnInfo(name = "search_query")
    val searchQuery: String
)
