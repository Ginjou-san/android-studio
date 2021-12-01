package com.example.myapplication.model

data class Test (
    val name : String,
    val salary: Int,
    val married: Boolean,
    val days: List <Days>,
    val employees: List <Employees>
)


data class Days (
    val sunday :String,
    val mondya :String,
    val tuesday :String,
    val wednesday :String,
    val thursday :String,
    val friday :String,
    val saturday :String,
        )

data class Employees (
    val name :String,
    val email: String
        )
