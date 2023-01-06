package com.otienosamwel.reads.ui.presentation.features.discover

import android.app.Activity
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.otienosamwel.reads.data.model.Item
import com.otienosamwel.reads.data.model.SearchItem
import com.otienosamwel.reads.data.model.SearchResult
import com.otienosamwel.reads.data.remote.GenreSearchResponse
import com.otienosamwel.reads.data.repository.search.SearchRepository
import com.otienosamwel.reads.utils.toast
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(private val searchRepository: SearchRepository) :
    ViewModel() {

    val discoverState = DiscoverState()
    val searchHistoryFlow = searchRepository.getAllSearchItems()


    ///book view-model cache
    private val bookCache = hashMapOf<String, MutableStateFlow<List<Item>>>()

    private var _searchResponseItems = MutableLiveData<SearchResult>()
    val searchResponseItems: LiveData<SearchResult> get() = _searchResponseItems

    fun search(activity: Activity) {
        if (discoverState.searchQuery.isNotBlank()) {
            val query = discoverState.searchQuery

            viewModelScope.launch(Dispatchers.IO) {
                searchRepository.insertSearchItem(
                    SearchItem(
                        time = System.currentTimeMillis(),
                        searchQuery = query
                    )
                )

                discoverState.isHistoryDropDownVisible = false
                discoverState.isSearchLoading = true
                val response = searchRepository.performSearch(query)
                discoverState.isSearchLoading = false


                if (!response.hasError) {
                    _searchResponseItems.postValue(response.result!!)
                } else {
                    withContext(Dispatchers.Main) {
                        activity.toast("An error occurred")
                    }
                }
            }
        }
    }

    private suspend fun getBooksByGenre(genre: String): GenreSearchResponse =
        withContext(Dispatchers.IO) {
            searchRepository.performGenreSearch(genre)
        }

    fun getGenreList(genre: String): MutableStateFlow<List<Item>> {
        Log.i(TAG, "getGenreList: called")
        return if (bookCache.containsKey(genre)) {
            bookCache[genre]!!
        } else {

            bookCache[genre] = MutableStateFlow(listOf())

            viewModelScope.launch(Dispatchers.IO) {
                val genreSearchResponse = getBooksByGenre(genre)

                if (!genreSearchResponse.hasError) {
                    val bookItems = genreSearchResponse.result!!.items!!
                    bookCache[genre]?.value = bookItems
                }
            }

            bookCache[genre]!!
        }
    }


    fun deleteSearchItem(searchItem: SearchItem) {
        viewModelScope.launch(Dispatchers.IO) {
            searchRepository.deleteSearchItem(searchItem)
        }
    }


    companion object {
        private const val TAG = "DISCOVER VIEW MODEL"
    }
}