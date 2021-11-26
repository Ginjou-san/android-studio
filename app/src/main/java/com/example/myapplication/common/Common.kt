package com.example.myapplication.common

import com.example.myapplication.retrofit.RetrofitClient
import com.example.myapplication.retrofit.RetrofitServices

object Common {
    private val BASE_URL = "https://imdb-api.com/en/"
    val retrofitService: RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)
}