package com.otienosamwel.reads.data.repository.book

import com.otienosamwel.reads.data.model.Author

interface BookRepository {

    suspend fun getBookOfTheWeek()


    suspend fun getAuthorOfTheWeek(): Author
}