package com.example.myapplication.retrofit

import com.example.myapplication.model.Items
import com.example.myapplication.model.Titles
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitServices {
    @GET("Top250Movies/k_ft56zq4q")
    fun getMovieList (): retrofit2.Call<Items>

    @GET("Title/k_ft56zq4q/{id}/Images")
    fun getTitleList (@Path("id") id: String): retrofit2.Call<Titles>
}