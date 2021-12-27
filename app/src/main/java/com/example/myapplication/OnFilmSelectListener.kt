package com.example.myapplication

import com.example.myapplication.model.Films

sealed interface OnFilmSelectListener{
    fun onSelect (films: Films)
    fun onBase (film: Films)
    fun onDelete(film: Films)

}

// в интерфейсе создаём fun onSelect, для обращения к нашему Data class