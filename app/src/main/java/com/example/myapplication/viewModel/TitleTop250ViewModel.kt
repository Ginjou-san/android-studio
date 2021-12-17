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

    val resultTitles = MutableStateFlow<Titles?>(null)


    fun load (id: String) {
        viewModelScope.launch {
            kotlin.runCatching { withContext (Dispatchers.IO){
                retrofit.getTitleList(id)
                 } }

                .onSuccess {
                    resultTitles.value = it
                }
                .onFailure { e ->
                    Log.e("Response", e.message,e)
                }
        }
    }
}
