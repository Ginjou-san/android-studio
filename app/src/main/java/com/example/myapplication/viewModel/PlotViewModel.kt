package com.example.myapplication.viewModel

import Titles
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PlotViewModel(State: SavedStateHandle): ViewModel() {

    val resultPlot = MutableStateFlow<Titles?>(null)

    init {
        resultPlot.value = State.get<Titles>("t")
        Log.e("Response", "Plot")
    }

}