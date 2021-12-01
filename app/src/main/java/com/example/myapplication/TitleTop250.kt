package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapter.MyTitleAdapter
import com.example.myapplication.model.Titles
import com.example.myapplication.retrofit.RetrofitServiecesTitle
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TitleTop250 : Fragment() {
    lateinit var mService: RetrofitServiecesTitle
//    lateinit var adapter: MyTitleAdapter
//    lateinit var layoutManager: LinearLayoutManager
//    lateinit var rvFilms: RecyclerView
//    lateinit var card: RecyclerView
    lateinit var tvTitle: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        return inflater.inflate(R.layout.fragment_title_top250, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvTitle = view.findViewById(R.id.title_title)


        getAllMovieList()
    }

    private fun getAllMovieList() {
        mService.getTitlesList("").enqueue(object : Callback<Titles> {
            override fun onFailure(call: Call<Titles>, t: Throwable) {
//                Log.i("test1", t.toString())
            }

            override fun onResponse(call: Call<Titles>, response: Response<Titles>) {
                val items = response.body()
                ui(items!!)

            }
        })
    }

    private fun ui(titles: Titles) {
        tvTitle.text = titles.title
    }

}


