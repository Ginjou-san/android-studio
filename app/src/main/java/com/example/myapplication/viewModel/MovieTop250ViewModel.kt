package com.example.myapplication.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.common.Common
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
    var textSearch : List<Films>? = null

    val numberList = MutableStateFlow(0)
    val timeList = MutableStateFlow(0)

    init {
        viewModelScope.launch {
            delay(2000)
            kotlin.runCatching {
                withContext(Dispatchers.IO) {
                    retrofit.getMovieList()
                }
            }
                .onSuccess {
                    val startTime = System.currentTimeMillis()
                    val result = retrofit.getMovieList()

                    resultMovie.value = result.items
                    textSearch = result.items
                    numberList.value = resultMovie.value!!.size
                    val totalTime = System.currentTimeMillis() - startTime
                    timeList.value = totalTime.toInt()
                    Log.e("Response", "MovieSuccess")
                }
                .onFailure { e ->
                    Log.e("Response", e.message, e)
                }
        }
    }

    fun clear (){
        resultMovie.value = textSearch
    }

    fun search(tearn: String) {
        val y =  textSearch?.filter{it.title.contains(tearn, ignoreCase = true)}
        resultMovie.value = y
    }
}


