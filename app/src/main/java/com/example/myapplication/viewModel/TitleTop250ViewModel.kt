package com.example.myapplication.viewModel

import Titles
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.common.Common
import com.example.myapplication.retrofit.RetrofitServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TitleTop250ViewModel(State: SavedStateHandle): ViewModel() {

    private val retrofit : RetrofitServices = Common.retrofitService

    val resultTitles = MutableStateFlow<Titles?>(null)


    init {


        viewModelScope.launch {
            kotlin.runCatching {
                withContext(Dispatchers.IO) {
                    State.get<String>("d")?.let { retrofit.getTitleList(it) } }
            }
                .onSuccess {
                    resultTitles.value = it
                    Log.e("Response", "title")
            }
                .onFailure { e ->
                 Log.e("Response", e.message, e)
            } }
    }
}
