package com.example.myapplication.data

import com.example.myapplication.model.Films
import kotlinx.coroutines.flow.MutableStateFlow
//
//class FilmsRepository(private val daoFilms: DaoFilms) {
//
//    val readAllData: MutableStateFlow<List<Films>?> = daoFilms.readAllData()
//
//    suspend fun add(film: FilmsId){
//        daoFilms.addFilms(film)
//    }
//}

//    suspend fun onBase(film: FilmsId){
////        val add = FilmsId(film.films, film.id)
////        daoFilms.addFilms(addFilms)
//        daoFilms.addFilms(add)
//    }
//}

//Репозиторий - это класс, который абстрагирует доступ к нескольким источникам данным. Репозиторий не являеться частью библиотек компонентов.
//Метод для разделение кода и для чистой архетектуры