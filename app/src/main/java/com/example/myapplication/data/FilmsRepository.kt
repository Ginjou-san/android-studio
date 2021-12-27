package com.example.myapplication.data

//class FilmsRepository(private val daoFilms: DaoFilms) {
//
//    val readAllData: MutableStateFlow<List<Films>?> = daoFilms.readAllData()
//
//    suspend fun addFilms(user: FilmsId){
//        daoFilms.addFilms(user)
//    }
//}

//Репозиторий - это класс, который абстрагирует доступ к нескольким источникам данным. Репозиторий не являеться частью библиотек компонентов.
//Метод для разделение кода и для чистой архетектуры