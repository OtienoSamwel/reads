package com.otienosamwel.reads.ui.presentation.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.otienosamwel.reads.data.model.Author
import com.otienosamwel.reads.domain.useCases.GetAuthorOfTheWeekUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAuthorOfTheWeekUseCase: GetAuthorOfTheWeekUseCase
) : ViewModel() {


    fun getBooks() {
        viewModelScope.launch(Dispatchers.IO) {
        }
    }


    fun getAuthorOfTheWeek(){
     //   val author : Author = getAuthorOfTheWeekUseCase
    }
}