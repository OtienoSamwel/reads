package com.example.reads.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.reads.data.model.Book
import com.example.reads.data.repository.ReadsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: ReadsRepository) : ViewModel() {
    private var _data = MutableLiveData<Book>()
    val data: LiveData<Book> = _data

    private var _status = MutableLiveData<String>()
    val status: LiveData<String> = _status
}