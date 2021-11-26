package com.example.myapplication.model

data class Movie(
    val id: String,
    val rank: Int,
    val title: String,
    val FullTitle: String,
    val year: Int,
    val image: String,
    val crew: String,
    val imDbRating: Float ,
    val imDbRatingCount: Int
)
