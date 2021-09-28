package com.example.reads.details.viewModel

import androidx.lifecycle.ViewModel
import com.example.reads.data.repository.ReadsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: ReadsRepository) : ViewModel() {
}