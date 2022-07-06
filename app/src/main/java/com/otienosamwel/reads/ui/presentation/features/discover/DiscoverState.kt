package com.otienosamwel.reads.ui.presentation.features.discover

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class DiscoverState {

    var searchQuery by mutableStateOf("")
        private set

    fun onSearchQueryChange(query: String) {
        isHistoryDropDownVisible = true
        searchQuery = query
    }

    var isSearchLoading by mutableStateOf(false)
    var isHistoryDropDownVisible by mutableStateOf(false)
}