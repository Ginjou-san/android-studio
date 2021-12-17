package com.example.myapplication.viewModel

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

class TitleTop250ViewModel: ViewModel() {

    private val retrofit : RetrofitServices = Common.retrofitService
//    val dataId = arguments?.getSerializable("f") as String
    val resultTitles = MutableStateFlow<List<Titles>?>(null)
//    val test = MutableStateFlow<Titles<>>(null)

    init {
        viewModelScope.launch {
            kotlin.runCatching { withContext (Dispatchers.IO){
                retrofit.getTitleList("t") } }

                .onSuccess { response ->
                    val result = retrofit.getTitleList("t")
                    resultTitles.value = result
                }
                .onFailure { e ->
                    Log.e("Response", e.message,e)
                }
        }
    }
    suspend fun load (id: String) {
        retrofit.getTitleList(id)
//        retrofit.getTitleList("t")
//        retrofit.getTitleList(id = "t")
    }
}
