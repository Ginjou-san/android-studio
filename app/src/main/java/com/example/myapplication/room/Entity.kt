package com.example.myapplication.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FilmsId (

    @PrimaryKey val idFilms: Int,
    val id: String?
    )

@Entity
data class ButtonId (

    @PrimaryKey val idButton: Int,
    val button: String?,
    val id: String?
    )

//Когда вы используете библиотеку сохранения состояния комнаты для храните данные вашего приложения,
// вы определяете сущности для представления объектов, которые вы хочу хранить.
// Каждая сущность соответствует таблице в соответствующей комнате. базы данных,
// и каждый экземпляр объекта представляет собой строку данных в соответствующая таблица.
//
//Это означает, что вы можете использовать сущности Room для определения своей базы данных.
// схема без написание любого SQL-кода.



//Вы определяете каждую сущность Room как класс, помеченный @Entity.
// Сущность Room включает в себя поля для каждого столбца в соответствующей таблице в базе данных,
// включая один или несколько столбцов, составляющих первичный ключ .