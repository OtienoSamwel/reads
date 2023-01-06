package com.otienosamwel.reads.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.otienosamwel.reads.data.model.Author

class Converters {

    private val gson = Gson()

    @TypeConverter
    fun jsonToAuthor(authorString: String): Author = gson.fromJson(authorString, Author::class.java)

    @TypeConverter
    fun authorToJson( author : Author) : String = gson.toJson(author)
}