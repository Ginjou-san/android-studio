package com.example.myapplication.adapter

import com.example.myapplication.model.Films

sealed interface OnFilmSelectListener{
    fun onSelect (films: Films)
}