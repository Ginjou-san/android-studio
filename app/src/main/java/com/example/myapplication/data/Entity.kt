package com.example.myapplication.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "films_table")
data class FilmsId (

    @PrimaryKey (autoGenerate = true  )
    val films:String,
    val id: String?
    )

//@Entity
//data class ButtonId (
//
//    @PrimaryKey val idButton: Int,
//    val button: String?,
//    val id: String?
//    )


//Аннотацией Entity нам необходимо пометить объект, который мы хотим хранить в базе данных.
// Понимает что мы будем записывать. ПРоверяет то мы пишем или нет
//Класс помечается аннотацией Entity. Объекты класса Employee будут использоваться при работе с базой данных.
// Например, мы будем получать их от базы при запросах данных и отправлять их в базу при вставке данных.

//Этот же класс Employee будет использован для создания таблицы в базе.
// В качестве имени таблицы будет использовано имя класса. А поля таблицы будут созданы в соответствии с полями класса.

//Entity класс используется для создания таблицы.
//По умолчанию в качестве имени таблицы используется имя этого класса.
//@PrimaryKey - Первичный ключ