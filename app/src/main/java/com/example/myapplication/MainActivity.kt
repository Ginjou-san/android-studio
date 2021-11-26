package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapter.MyMovieAdapter
import com.example.myapplication.retrofit.RetrofitServices
import com.example.myapplication.common.Common
import com.example.myapplication.model.Items
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    lateinit var mService: RetrofitServices
    lateinit var adapter: MyMovieAdapter
    lateinit var layoutManager: LinearLayoutManager
    lateinit var rvFilms: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvFilms = findViewById(R.id.recyclerMovieList)
        mService = Common.retrofitService
        rvFilms.setHasFixedSize (true)
        layoutManager = LinearLayoutManager(this)
        rvFilms.layoutManager = layoutManager


        getAllMovieList()
    }

    private fun getAllMovieList() {
        mService.getMovieList().enqueue(object : Callback<Items>{
            override fun onFailure(call: Call<Items>, t: Throwable) {
                Log.i("test1", t.toString())
            }
            override fun onResponse(call: Call<Items>, response: Response<Items>){
               val items = response.body()
                adapter = MyMovieAdapter (baseContext,items!!.items)
                adapter.notifyDataSetChanged()
                rvFilms.adapter = adapter


                Log.i("test2", response.toString())
            }
        })
    }
}



