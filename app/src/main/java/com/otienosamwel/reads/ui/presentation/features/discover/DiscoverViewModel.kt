package com.otienosamwel.reads.ui.presentation.features.discover

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.otienosamwel.reads.data.model.SearchItem
import com.otienosamwel.reads.data.model.SearchResult
import com.otienosamwel.reads.data.repository.search.SearchRepository
import com.otienosamwel.reads.utils.toast
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(private val searchRepository: SearchRepository) :
    ViewModel() {
    val discoverState = DiscoverState()
    val searchHistoryFlow = searchRepository.getAllSearchItems()

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

    fun deleteSearchItem(searchItem: SearchItem) {
        viewModelScope.launch(Dispatchers.IO) {
            searchRepository.deleteSearchItem(searchItem)
        }
    }


}