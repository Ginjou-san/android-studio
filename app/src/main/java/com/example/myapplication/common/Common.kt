package com.example.myapplication.common

import com.example.myapplication.retrofit.RetrofitClient
import com.example.myapplication.retrofit.RetrofitServices

object Common {
    private val BASE_URL = "https://imdb-api.com/en/API/" //создаем переменную, называем ее BASE_URL и в нее ложим  ссылку с которой парсим данные
    val retrofitService: RetrofitServices  //Создаем переменную retrofitServices, у нее есть метод get()
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)     // к нему присваиваем RetrofitClient,
//а уже потом к RetrofitClient .сетим. метод getClient c параметром RetrofitServices::class.java
}

//В get() мы указываем что нам вернуть при обращении к переменной, в нашем случае к retrofitService

//sett() определяет логику установки значения переменной.
// В блоке set проверяем, входит ли устанавливаемое значение в диапазон допустимых значений.
// Если входит, то есть если значение корректно,