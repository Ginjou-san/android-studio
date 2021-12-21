package com.example.myapplication.viewModel

import Titles
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

import kotlinx.coroutines.flow.MutableStateFlow


class ActorsViewModel(State: SavedStateHandle): ViewModel() {

    val resultActor = MutableStateFlow<Titles?>(null)


    init {
        resultActor.value = State.get<Titles>("t")
        Log.e("Response", "Actor")
    }


}
