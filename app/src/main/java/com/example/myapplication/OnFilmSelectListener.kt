package com.example.myapplication

import com.example.myapplication.model.Films

sealed interface OnFilmSelectListener{
    fun onSelect (films: Films)
}