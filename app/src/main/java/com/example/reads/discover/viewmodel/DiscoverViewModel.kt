package com.example.reads.discover.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.reads.data.model.Book
import com.example.reads.data.model.Item
import com.example.reads.data.repository.ReadsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(private val repository: ReadsRepository) : ViewModel() {

    val data: MutableLiveData<Book> = MutableLiveData<Book>()

    fun getGenreBooks(genre: String): LiveData<PagingData<Item>> {
        return repository.getGenreBooksPaging(genre)
    }
}