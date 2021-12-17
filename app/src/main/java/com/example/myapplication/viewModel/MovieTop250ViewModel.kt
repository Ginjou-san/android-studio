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

    private val retrofit : RetrofitServices = Common.retrofitService

    val _result = MutableStateFlow<List<Films>?>(null)
    val numberList = MutableStateFlow(0)
    val timeList = MutableStateFlow(0)


    init {
        viewModelScope.launch {
            delay(2000)
            kotlin.runCatching { withContext (Dispatchers.IO){
                retrofit.getMovieList() } }

                .onSuccess {
                    val startTime = System.currentTimeMillis()
                    val result = retrofit.getMovieList()
                    _result.value = result.items
                    numberList.value = _result.value!!.size
                    val totalTime = System.currentTimeMillis() - startTime
                    timeList.value = totalTime.toInt()
                }
                .onFailure { e ->
                    Log.e("Response" , e.message,e)
                }
        }
    }

}
