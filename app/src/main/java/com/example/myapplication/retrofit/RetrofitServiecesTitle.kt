package com.example.myapplication.retrofit

import com.example.myapplication.model.Titles
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitServiecesTitle {
    @GET("API/Title/k_ft56zq4q/{id}/Images")
    fun getTitlesList(@Path("id") id: String): Call<Titles>
}