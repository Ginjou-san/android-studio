package com.example.myapplication.viewModel

import Actor
import Titles
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.common.Common
import com.example.myapplication.retrofit.RetrofitServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ActorsViewModel: ViewModel() {

    private val retrofit : RetrofitServices = Common.retrofitService
    val resultActor = MutableStateFlow<List<Actor>?>(null)

//    init {
//        viewModelScope.launch {
//            kotlin.runCatching { withContext (Dispatchers.IO){
//                retrofit.getTitleList("t") } }
//
//                .onSuccess {
//
//                    val result = retrofit.getTitleList("t")
//                    resultActor.value = result.actorList
//                }
//                .onFailure { e ->
//                    Log.e("Response" , e.message,e)
//                }
//        }
//    }
//    fun load (id: String) {
//        viewModelScope.launch {
//            kotlin.runCatching { withContext (Dispatchers.IO){
//                retrofit.getTitleList(id)
//            } }
//
//                .onSuccess {
//                    resultActor.value = it
//                }
//                .onFailure { e ->
//                    Log.e("Response", e.message,e)
//                }
//        }
//    }
}