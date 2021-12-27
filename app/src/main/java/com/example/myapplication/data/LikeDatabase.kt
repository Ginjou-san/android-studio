package com.example.myapplication.data

import android.content.Context

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FilmsId::class], version = 1, exportSchema = false)
    abstract class FilmsDatabase : RoomDatabase() {
    abstract fun daoFilms(): DaoFilms

    companion object {
        private var INSTANCE: FilmsDatabase? = null
        fun getDatabase(context: Context): FilmsDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    FilmsDatabase::class.java,
                    "film_test")
                    .build()
            }
            return INSTANCE as FilmsDatabase
        }
    }
}
//Аннотацией Database помечаем основной класс по работе с базой данных.
//Этот класс должен быть абстрактным и наследовать RoomDatabase.
//В параметрах аннотации Database указываем, какие Entity будут использоваться, и версию базы.
//Для каждого Entity класса из списка entities будет создана таблица.
//
//В Database классе необходимо описать абстрактные методы для получения Dao объектов, которые вам понадобятся.