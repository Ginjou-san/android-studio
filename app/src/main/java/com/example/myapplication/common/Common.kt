package com.example.myapplication.common

import com.example.myapplication.retrofit.RetrofitClient
import com.example.myapplication.retrofit.RetrofitServices

object Common {
    private val BASE_URL = "https://imdb-api.com/en/API/" //создаем переменную, называем ее BASE_URL и в нее мы должны положить ссылку с которой парсим данные
    val retrofitService: RetrofitServices  //но не класть последнюю ветку, так как именно с нее мы получаем данные. Создаем переменную retrofitServices,
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)     //у нее есть метод get() к нему мы присваиваем RetrofitClient,
//а уже потом к RetrofitClient мы сетим метод getClient c параметром RetrofitServices::class.java

}



