package com.otienosamwel.reads.data.repository.search

import com.otienosamwel.reads.data.local.SearchDao
import com.otienosamwel.reads.data.model.SearchItem
import com.otienosamwel.reads.data.remote.GenreSearchResponse
import com.otienosamwel.reads.data.remote.NetworkService
import com.otienosamwel.reads.data.remote.SearchResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchDao: SearchDao,
    private val networkService: NetworkService
) : SearchRepository {
    override suspend fun insertSearchItem(searchItem: SearchItem) = withContext(Dispatchers.IO) {
        searchDao.insertSearchItem(searchItem)
    }

    override suspend fun getSearchItemById(id: Int) = withContext(Dispatchers.IO) {
        return@withContext searchDao.getSearchItemById(id)
    }

    override suspend fun deleteSearchItem(searchItem: SearchItem) =
        withContext(Dispatchers.IO) {
            searchDao.deleteSearchItem(searchItem)
        }

    override suspend fun deleteAllSearchItems() = withContext(Dispatchers.IO) {
        searchDao.deleteAllSearchItems()
    }

    override fun getAllSearchItems(): Flow<List<SearchItem>> {
        return searchDao.getAllSearchItems()
    }

    override suspend fun performSearch(query: String): SearchResponse {
        return networkService.searchBooks(query)
    }

    override suspend fun performGenreSearch(genre: String): GenreSearchResponse {
        return networkService.getBooksByGenre(genre)
    }

}