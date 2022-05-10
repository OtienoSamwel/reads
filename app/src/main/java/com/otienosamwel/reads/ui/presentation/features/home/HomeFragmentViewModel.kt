package com.otienosamwel.reads.ui.presentation.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeFragmentViewModel @Inject constructor() : ViewModel() {


    fun getBooks() {
        viewModelScope.launch(Dispatchers.IO) {
        }
    }
}