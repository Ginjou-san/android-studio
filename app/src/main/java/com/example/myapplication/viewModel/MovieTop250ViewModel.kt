package com.example.myapplication.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.common.Common
import com.example.myapplication.data.FilmsDatabase
import com.example.myapplication.data.FilmsId
import com.example.myapplication.model.Films
import com.example.myapplication.retrofit.RetrofitServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieTop250ViewModel: ViewModel() {

    private val retrofit: RetrofitServices = Common.retrofitService
    var resultMovie = MutableStateFlow<List<Films>?>(null)
    val numberList = MutableStateFlow(0)
    val timeList = MutableStateFlow(0)
    var textSearch: List<Films>? = null
    private val dataBase = FilmsDatabase.getDatabase()

    init {
        viewModelScope.launch {
            delay(2000)
            kotlin.runCatching { withContext(Dispatchers.IO) {
                    val dbList = dataBase.daoFilms().load()
                    val networkList = retrofit.getMovieList()
                    dbList to networkList

                }
            }
                .onSuccess { (dbList, networkList) ->
                    val startTime = System.currentTimeMillis()
                    resultMovie.value = networkList.items
                    textSearch = networkList.items
                    numberList.value = resultMovie.value!!.size
                    val totalTime = System.currentTimeMillis() - startTime
                    timeList.value = totalTime.toInt()

                    dbList.forEach { save ->
                        val xxx = save.id
                        networkList.items.forEach { retro ->
                            if (xxx == retro.id){
                                retro.favorite = true
                            }
                        }
                    }
                    Log.e("Response", "MovieSuccess")
                }
                .onFailure { e ->
                    Log.e("Response", e.message, e)
                }
        }
    }

    fun clear() {
        resultMovie.value = textSearch
    }

    fun search(tearn: String) {
        val check = textSearch?.filter {
            it.title.contains(
                tearn,
                ignoreCase = true
            )
        } //Производиться фильтрация что бы совпадало с tearn и выводиться в масиве. Всё что совпадает мы выводим в новоый массив и возращаем обратно
        resultMovie.value = check                                                     //MutableStateFlow через метод value, засовываем в лист фильм
    }

    fun insert(film: Films){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                dataBase.daoFilms().insert(FilmsId(film.id))
                Log.i("base", "save")
            }
        }
    }

    fun delete(film: Films){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                dataBase.daoFilms().delete(FilmsId(film.id))
                Log.i("base", "delete")
            }
            }

    }

    fun onFavorite(selectedFilms: Films){
        val result = resultMovie.value?.map {  film ->
            if (film.id == selectedFilms.id){
                film.copy(favorite = film.favorite.not())
            }else{
                film
            }
        }
        viewModelScope.launch {
            resultMovie.emit(result) //результат который мы получили записываем в MutableStateFlow
        }
    }
}
//
//fun onFavorite(selectedFilms: Films){
//    val result = resultMovie.value?.map {  film ->
//        if (film.id == selectedFilms.id){
//            film.copy(favorite = film.favorite.not())
//        }else{
//            film
//        }
//    }
//    viewModelScope.launch {
//        resultMovie.emit(result) //результат который мы получили записываем в MutableStateFlow
//    }
//}
//    fun favorite(favoriteList: List<FavoriteList>){
//        viewModelScope.launch {                                     //Запускаем корутину, с атрибутом хранения значение времени,  созданя объектов.
//            withContext(Dispatchers.IO) {                           //Переключаем контекст текущей корутины, когда выполняем блок Dispatchers.IO. Делаем задачу фоновой, не блокирующей основной поток
//                var operation = toString()
//                favoriteList.onEach {                               //выполняем операцию, с каждым элементом последовательности в середине цепочки вызовов.
//                    operation = it.id.toString()
//                    var has = resultMovie.value.toString().contains(operation) //указываем что она равна переменной resultMovie, в нутри которой лежит Стринг. И содержит переменную operation
//
//                }
//            }
//        }
//    }
//Оператор filter выполняет фильтрацию объектов в потоке.
// В качестве параметра он принимает функцию-условие,
// которая получает объект потока и возвращает true (если объект подходит фильтрацию) и false (если не проходит).

//onEach упрощает отладочные списки и последовательности
//Используйте onEach для выполнения операции с каждым элементом последовательности в середине цепочки вызовов.

//value то что лежит внутри. Если его не будет типы не будут совпадать

//contains() позволит убедиться, что нужный элемент присутствует в списке. Перебирает массив на присуцтвие чего либо (содержит)