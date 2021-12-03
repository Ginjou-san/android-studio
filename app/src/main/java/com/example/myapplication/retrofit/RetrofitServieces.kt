package com.example.myapplication.retrofit

import Titles
import com.example.myapplication.model.Items
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitServices {
    @GET("Top250Movies/k_k9cqlnuz")
    fun getMovieList (): retrofit2.Call<Items>

    @GET("Title/k_k9cqlnuz/{id}/Images")
    fun getTitleList (@Path("id") id: String): retrofit2.Call<Titles>
}