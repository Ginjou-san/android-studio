package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapter.MyMovieAdapter
import com.example.myapplication.retrofit.RetrofitServices
import com.example.myapplication.common.Common
import com.example.myapplication.data.FilmsDatabase
import com.example.myapplication.model.Items
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) { //он вызывается, когда приложение создает и отображает Activity
    super.onCreate(savedInstanceState)//это вызов метода родительского класса, выполняющий необходимые процедуры, его мы не трогаем.
        FilmsDatabase.getDatabase(this)
    setContentView(R.layout.activity_main) //Метод setContentView(int) – устанавливает содержимое Activity из layout-файла.
    // Но в качестве аргумента мы указываем не путь к layout-файлу (res/layout/activity_main.xml), а константу, которая является ID файла.
    // Эта константа генерируется автоматически в файле R.java, который мы пока трогать не будем.
    // В этом классе будут храниться сгенерированные ID для всех ресурсов проекта (из папки res/*), чтобы мы могли к ним обращаться.
    // Имена этих ID-констант совпадают с именами файлов ресурсов (без расширений).

}

}



