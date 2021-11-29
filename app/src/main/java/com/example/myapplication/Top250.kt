package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapter.MyMovieAdapter
import com.example.myapplication.common.Common
import com.example.myapplication.model.Items
import com.example.myapplication.retrofit.RetrofitServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Top250 : Fragment() {
    lateinit var mService: RetrofitServices
    lateinit var adapter: MyMovieAdapter
    lateinit var layoutManager: LinearLayoutManager
    lateinit var rvFilms: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_top250, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvFilms = view.findViewById(R.id.list)
        mService = Common.retrofitService
        rvFilms.setHasFixedSize (true)
        layoutManager = LinearLayoutManager(context)
        rvFilms.layoutManager = layoutManager

        getAllMovieList()
    }

    private fun getAllMovieList() {
        mService.getMovieList().enqueue(object : Callback<Items> {
            override fun onFailure(call: Call<Items>, t: Throwable) {
                Log.i("test1", t.toString())
            }
            override fun onResponse(call: Call<Items>, response: Response<Items>){
                val items = response.body()
                adapter = MyMovieAdapter (context!!,items!!.items)
                adapter.notifyDataSetChanged()
                rvFilms.adapter = adapter
                Log.i("test2", response.toString())
            }
        })
    }
}