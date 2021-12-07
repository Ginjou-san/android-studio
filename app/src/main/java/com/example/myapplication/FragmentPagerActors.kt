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
import com.example.myapplication.adapter.MyPagerActorsAdapter
import com.example.myapplication.common.Common
import com.example.myapplication.databinding.RecyclerViewActorsBinding
import com.example.myapplication.retrofit.RetrofitServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentViewPagerActors : Fragment() {
    lateinit var mService: RetrofitServices
    lateinit var adapter: MyPagerActorsAdapter
    lateinit var linerLayoutManager: LinearLayoutManager
    lateinit var rvFilms: RecyclerView
    private var _binding: RecyclerViewActorsBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = RecyclerViewActorsBinding.inflate(inflater, container, false) // указываем с какой xml работаем
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvFilms = view.findViewById(R.id.recycler_actors)
        mService = Common.retrofitService
//        rvFilms.setHasFixedSize (true)
        linerLayoutManager = LinearLayoutManager(context)
        rvFilms.layoutManager = linerLayoutManager


        getAllMovieList()
    }

    private fun getAllMovieList() {
        val titleId = arguments?.getSerializable("f")
        mService.getTitleList(titleId as String).enqueue(object : Callback<Actor> {
            override fun onFailure(call: Call<Actor>, t: Throwable) {
            }

            override fun onResponse(call: Call<Actor>, response: Response<Actor>){
                val titlesItems = response.body()
                adapter = MyPagerActorsAdapter (context!!,titlesItems.name.)
                adapter.notifyDataSetChanged()
                rvFilms.adapter = adapter
            }
        })
    }


}