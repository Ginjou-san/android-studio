package com.example.myapplication

import Titles
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.adapter.MyTitleAdapter
import com.example.myapplication.common.Common
import com.example.myapplication.model.Items
import com.example.myapplication.retrofit.RetrofitServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TitleTop250 : Fragment() {
    lateinit var mService: RetrofitServices
    lateinit var tvTitles: TextView
    lateinit var tvRating:TextView
    lateinit var tvGenres:TextView
    lateinit var tvYear:TextView
    lateinit var tvDirector:TextView
    lateinit var tvMetaCriticRating:TextView
    lateinit var tvStarList:TextView
    lateinit var tvBoxOffice:TextView
    lateinit var tvPLOT:TextView
    lateinit var adapter: MyTitleAdapter
    lateinit var rvFilms: RecyclerView
//    private var _binding: FragmentTitleTop250Binding? = null
//    private val binding get() = _binding!!
    lateinit var tvImageMovies:ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

//        _binding = FragmentTitleTop250Binding.inflate(inflater, container, false)
        return inflater.inflate(R.layout.fragment_title_top250, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val horizontalScrollView = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        rvFilms = view.findViewById(R.id.title_Liner)
        mService = Common.retrofitService
        rvFilms.layoutManager = horizontalScrollView
        tvTitles = view.findViewById(R.id.title_title)
        tvRating = view.findViewById(R.id.title_imDbRating)
        tvGenres = view.findViewById(R.id.title_genres)
        tvYear = view.findViewById(R.id.title_year)
        tvDirector = view.findViewById(R.id.title_director)
        tvMetaCriticRating = view.findViewById(R.id.title_metaCriticRating)
        tvStarList = view.findViewById(R.id.title_star)
        tvBoxOffice = view.findViewById(R.id.title_boxOffice)
        tvPLOT = view.findViewById(R.id.title_plot)
        tvImageMovies = view.findViewById(R.id.title_image_movie)

        getAllMovieList()
    }

    private fun getAllMovieList() {
        val titleId = arguments?.getSerializable("f") as String
        mService.getTitleList(titleId).enqueue(object : Callback<Titles> {
            override fun onFailure(call: Call<Titles>, t: Throwable) {
//                Log.i("test1", t.toString())
            }

            override fun onResponse(call: Call<Titles>, response: Response<Titles>) {
                val titlesItems = response.body() ?: return
                ui(titlesItems)
                adapter = MyTitleAdapter (context!!, titlesItems.images.items)
                adapter.notifyDataSetChanged()
                rvFilms.adapter = adapter
            }
        })
    }

    private fun ui(titles: Titles) {
        tvTitles.text = titles.title
        tvRating.text = titles.imDbRating
        tvGenres.text = titles.genres
        tvYear.text = titles.year
        tvDirector.text = titles.directors
        tvMetaCriticRating.text = titles.metacriticRating
        tvStarList.text = titles.stars
        tvBoxOffice.text = titles.boxOffice.budget
        tvPLOT.text = titles.plot
        Glide.with(tvImageMovies.context).load(titles.image).into(tvImageMovies)
    }

}


