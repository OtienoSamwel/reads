package com.example.reads.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reads.data.remote.api.Api
import com.example.reads.data.remote.model.Book
import kotlinx.coroutines.launch
import java.lang.Exception

class SearchViewModel: ViewModel() {
    private var _data = MutableLiveData<Book>()
    val data : LiveData<Book> = _data

    private var _status = MutableLiveData<String>()
    val status : LiveData<String> = _status

    init {
        viewModelScope.launch {
            try {
                _data.value = Api.service.getBooks()
            }catch(e : Exception){
                _status.value = "${e.message}"
            }

        }
    }
}