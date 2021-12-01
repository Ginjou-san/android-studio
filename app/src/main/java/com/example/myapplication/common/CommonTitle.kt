package com.example.myapplication.common

import com.example.myapplication.retrofit.RetrofitClient
import com.example.myapplication.retrofit.RetrofitServiecesTitle

object CommonTitle {
    private val BASE_URL = "https://imdb-api.com/ru/"
    val retrofitServiceTitle: RetrofitServiecesTitle
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServiecesTitle::class.java)
}