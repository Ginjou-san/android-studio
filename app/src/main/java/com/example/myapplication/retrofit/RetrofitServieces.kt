package com.example.myapplication.retrofit

import Titles
import com.example.myapplication.model.Items
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitServices {
    @GET("Top250Movies/k_k9cqlnuz") //Создаем Get запрос в скобках пишем кавычки, а в кавычках указывает ветку с которой будем парсить данные
    fun getMovieList (): retrofit2.Call<Items>

    @GET("Title/k_k9cqlnuz/{id}/Images")
    fun getTitleList (@Path("id") id: String): retrofit2.Call<Titles> //возвращает  Call  типа Titles
}

//Парсинг — это процесс автоматического сбора данных и их структурирования.

//GET — запрашивает данные с определенного ресурса(сайта)
//POST — отправляет данные на сервер для последующей обработки


