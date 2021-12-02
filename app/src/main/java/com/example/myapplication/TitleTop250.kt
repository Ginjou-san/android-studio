package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.myapplication.common.Common
import com.example.myapplication.databinding.FragmentTitleTop250Binding
import com.example.myapplication.databinding.FragmentTop250Binding
import com.example.myapplication.model.Titles
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
    lateinit var tvImageMovies:ImageView
//    lateinit var tvCardImage:ImageView
    private var _binding: FragmentTitleTop250Binding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        return inflater.inflate(R.layout.fragment_title_top250, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mService = Common.retrofitService
        tvTitles = view.findViewById(R.id.title_title)
        tvRating = view.findViewById(R.id.title_imDbRating)
        tvGenres = view.findViewById(R.id.title_genres)
        tvYear = view.findViewById(R.id.title_year)
        tvDirector = view.findViewById(R.id.title_director)
        tvMetaCriticRating = view.findViewById(R.id.title_metaCriticRating)
        tvStarList = view.findViewById(R.id.title_star_List)
        tvBoxOffice = view.findViewById(R.id.title_boxOffice)
        tvPLOT = view.findViewById(R.id.title_plot)
        tvImageMovies = view.findViewById(R.id.title_image_movie)
//        tvCardImage = view.findViewById(R.id.card_image)

        getAllMovieList()
    }

    private fun getAllMovieList() {
        mService.getTitleList("tt0111161").enqueue(object : Callback<Titles> {
            override fun onFailure(call: Call<Titles>, t: Throwable) {
//                Log.i("test1", t.toString())
            }

            override fun onResponse(call: Call<Titles>, response: Response<Titles>) {
                val items = response.body()
//                if(items == null)
//                    response

                ui(items!!)

            }
        })
    }

    private fun ui(titles: Titles) {
        tvTitles.text = titles.title
        tvRating.text = titles.ratings
        tvGenres.text = titles.genres
        tvYear.text = titles.year
        tvDirector.text = titles.directors
        tvMetaCriticRating.text = titles.metacriticRating
        tvStarList.text = titles.stars
        tvBoxOffice.text = titles.boxOffice
        tvPLOT.text = titles.plotLocal
        Glide.with(tvImageMovies.context).load(titles.image).into(tvImageMovies)
//        Glide.with(tvCardImage.context).load(titles.images).into(tvCardImage)

    }

}


