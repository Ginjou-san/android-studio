package com.example.myapplication.room

import android.content.Context

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FilmsId::class], version = 1, exportSchema = false)
    abstract class FilmsDatabase : RoomDatabase() {
        abstract fun DaoFilms(): DaoFilms
        companion object{
            @Volatile
            private var INSTANCE: FilmsDatabase? = null

            fun getDatabase (context: Context): FilmsDatabase {
                val tempInstance = INSTANCE
                if (tempInstance != null){
                    return tempInstance
                }
                synchronized(this){
                 val instance = Room.databaseBuilder(
                     context.applicationContext,
                     FilmsDatabase::class.java,
                 "film_Database"
                 ).build()
                 INSTANCE = instance
                 return instance
                }
            }
        }
    }


//Аннотацией Database помечаем основной класс по работе с базой данных.
//Этот класс должен быть абстрактным и наследовать RoomDatabase.

//В параметрах аннотации Database указываем, какие Entity будут использоваться, и версию базы.
//Для каждого Entity класса из списка entities будет создана таблица.
//
//В Database классе необходимо описать абстрактные методы для получения Dao объектов, которые вам понадобятся.