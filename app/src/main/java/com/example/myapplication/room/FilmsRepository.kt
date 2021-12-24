package com.example.myapplication.room

import androidx.lifecycle.LiveData
import com.example.myapplication.model.Films
import com.example.myapplication.room.DaoFilms
import com.example.myapplication.room.FilmsId

class FilmsRepository(private val daoFilms: DaoFilms) {

    val readAllData: List<Films> = daoFilms.readAllData()

    suspend fun addFilms(user: FilmsId){
        daoFilms.addFilms(user)
    }
}


//Репозиторий - это класс, который абстрагирует доступ к нескольким источникам данным. Репозиторий не являеться частью библиотек компонентов.
//Метод для разделение кода и для чистой архетектуры кода