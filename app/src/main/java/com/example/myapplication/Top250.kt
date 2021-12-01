package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapter.MyMovieAdapter
import com.example.myapplication.adapter.OnFilmSelectListener
import com.example.myapplication.common.Common
import com.example.myapplication.databinding.FilmIteamBinding
import com.example.myapplication.model.Films
import com.example.myapplication.model.Items
import com.example.myapplication.retrofit.RetrofitServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Top250 : Fragment(), OnFilmSelectListener {
    lateinit var mService: RetrofitServices
    lateinit var adapter: MyMovieAdapter
    lateinit var layoutManager: LinearLayoutManager
    lateinit var rvFilms: RecyclerView
    private var _binding: FilmIteamBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FilmIteamBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvFilms = view.findViewById(R.id.list)
        mService = Common.retrofitService
        rvFilms.setHasFixedSize (true)
        layoutManager = LinearLayoutManager(context)
        rvFilms.layoutManager = layoutManager

        val button:ImageView = view.findViewById(R.id.image_movie)

//        button.setOnClickListener {
//            val bundle = Bundle()
//            bundle.putSerializable("f", import)
//            findNavController().navigate(R.id.fragment_title, bundle)
//
//        }

        binding.imageMovie.setOnClickListener{
            findNavController().navigate(R.id.action_top250_layout_to_title_250)
        }

        getAllMovieList()
    }

    private fun getAllMovieList() {
        mService.getMovieList().enqueue(object : Callback<Items> {
            override fun onFailure(call: Call<Items>, t: Throwable) {
//                Log.i("test1", t.toString())
            }
            override fun onResponse(call: Call<Items>, response: Response<Items>){
                val items = response.body()
                adapter = MyMovieAdapter (context!!,items!!.items, this@Top250 )
                adapter.notifyDataSetChanged()
                rvFilms.adapter = adapter
//                Log.i("test2", response.toString())
            }
        })
    }

    override fun onSelect (films: Films){

    }

    }



//data class IdClass(
//    val id: String
//
//):Serializable