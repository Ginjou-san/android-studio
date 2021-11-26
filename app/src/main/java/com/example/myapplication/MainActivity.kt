package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myapplication.retrofit.RetrofitServices
import com.example.myapplication.common.Common
import com.example.myapplication.model.Items
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var mService: RetrofitServices

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mService = Common.retrofitService
        getAllMovieList()
    }

    private fun getAllMovieList() {
        mService.getMovieList().enqueue(object : Callback<Items>{
            override fun onFailure(call: Call<Items>, t: Throwable) {
                Log.i("test1", t.toString())
            }
            override fun onResponse(call: Call<Items>, response: Response<Items>){
                Log.i("test2", response.toString())
            }
        })
    }
}



