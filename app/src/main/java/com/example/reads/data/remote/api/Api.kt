package com.example.reads.data.remote.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Api {
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://www.googleapis.com")
        .build()

    val service: Endpoints by lazy {
        retrofit.create(Endpoints::class.java)
    }
}