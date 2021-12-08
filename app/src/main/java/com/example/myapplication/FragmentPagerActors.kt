package com.example.myapplication

import Actor
import Titles
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapter.*
import okhttp3.Response

class FragmentViewPagerActors : Fragment() {
//    lateinit var mService: RetrofitServices
    lateinit var adapter: MyPagerActorsAdapter
    lateinit var linerLayoutManager: LinearLayoutManager
    lateinit var rvFilms: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.recycler_view_actors, container, false)
    }

    override fun onViewCreated(view: View,  savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val verticalScrollView = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        rvFilms = view.findViewById(R.id.recycler_actors)
//        mService = Common.retrofitService
//        rvFilms.setHasFixedSize (true)
        linerLayoutManager = verticalScrollView
        rvFilms.layoutManager = linerLayoutManager
      rvFilms.adapter = MyPagerActorsAdapter(Actor)
//        rvFilms.adapter = adapter

//        getAllMovieList()
    }

}

