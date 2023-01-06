package com.otienosamwel.reads.data.repository.book

import com.otienosamwel.reads.data.local.AuthorDao
import com.otienosamwel.reads.data.model.Author
import com.otienosamwel.reads.data.model.ServerBook
import com.otienosamwel.reads.data.remote.NetworkService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

