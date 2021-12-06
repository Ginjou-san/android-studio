package com.example.myapplication.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var retrofit: Retrofit? = null      //далее создаем переменную retrofit типа Retrofit


    fun getClient(baseUrl: String): Retrofit { //после этого создаем функцию и называем ее getCleint(baseUrl: String) и тип возвращаемого значения Retrofit

        if (retrofit == null) {     //В теле функции необходимо выполнить проверку retrofit'a на null и если ретрофит равен null
            retrofit = Retrofit.Builder() //и если ретрофит равен null
                .baseUrl(baseUrl)      //мы к ретрофиту присваиваем Retrofit.Builder() присоединяем baseUrl с параметром baseUrl
                .addConverterFactory(GsonConverterFactory.create())   //далее присоединяем метод addconverterFactory c параметром GsonConverterFactory.create()
                .build()   // и билдим с помощью метода build()
        }
        return retrofit!!   //и возвращаем ретрофит в ненулевой тип
    }
}
