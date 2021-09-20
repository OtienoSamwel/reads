package com.example.reads.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reads.data.model.Book
import com.example.reads.data.remote.api.Api
import com.example.reads.data.repository.ReadsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeFragmentViewModel @Inject constructor(val repository: ReadsRepository) : ViewModel() {

    val data: MutableLiveData<Book> = MutableLiveData<Book>()
    fun getDbBooks() {
        viewModelScope.launch {
            repository.getDbBooks()
        }
    }

    fun getBooks() {
        viewModelScope.launch(Dispatchers.IO) {
            data.postValue(repository.getBooks())
        }
    }
}