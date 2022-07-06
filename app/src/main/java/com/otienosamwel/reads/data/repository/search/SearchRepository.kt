package com.otienosamwel.reads.data.repository.search

import com.otienosamwel.reads.data.model.SearchItem
import com.otienosamwel.reads.data.remote.SearchResponse
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    suspend fun insertSearchItem(searchItem: SearchItem)

    suspend fun getSearchItemById(id: Int): Flow<SearchItem>

    suspend fun deleteSearchItem(searchItem: SearchItem)

    suspend fun deleteAllSearchItems()

    fun getAllSearchItems(): Flow<List<SearchItem>>

    suspend fun performSearch(query: String): SearchResponse
}