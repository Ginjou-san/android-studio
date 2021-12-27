package com.example.myapplication.model

data class Films (
    val id: String,
    val rank: Int,
    val title: String,
    val fullTitle: String,
    val year: Int,
    val image: String,
    val crew: String,
    val imDbRating: Float ,
    val imDbRatingCount: Int,
    val favorite: Boolean
)
