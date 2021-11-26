package com.example.myapplication.retrofit

import com.example.myapplication.model.Items
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitServices {
    @GET("API/Top250Movies/k_ft56zq4q")
    fun getMovieList(): Call<Items>
}