package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapter.MyMovieAdapter
import com.example.myapplication.common.Common
import com.example.myapplication.databinding.FragmentTop250Binding
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
    private var _binding: FragmentTop250Binding? = null
    private val binding get() = _binding!!

//    мы создадим переменные, и чтобы не объявлять их типа null объявим их через
//    lateinit var mService: RetrofitServices, нам необходимо создать еще 3 таких,
//    а именно: LinearLayoutManager, MyMovieAdapter, AlertDialog.

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View ?{

        _binding = FragmentTop250Binding.inflate(inflater, container, false) // делаем кликабельными объект FragmentTop250Binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvFilms = view.findViewById(R.id.list)  //...
        mService = Common.retrofitService       //В методе onViewCreated мы к RetrofitServices присваиваем Common.retrofitServices.
        rvFilms.setHasFixedSize (true)          //recyclerView мы присоединяем  setHasFixedSize(true) благодаря этому методу мы сможем оптимизировать свой список
        layoutManager = LinearLayoutManager(context)
        rvFilms.layoutManager = layoutManager   // после мы к нашему layoutManager присваиваем LinearLayoutManager(context).

        getAllMovieList()
    }

    private fun getAllMovieList() {
        mService.getMovieList().enqueue(object : Callback<Items> { // к mService добавляем метод getMovieList .enqueue object: Callback<MutableList>
            override fun onFailure(call: Call<Items>, t: Throwable) {
            }
            override fun onResponse(call: Call<Items>, response: Response<Items>){
                val items = response.body()
                adapter = MyMovieAdapter (context!!,items!!.items, this@Top250 )    // мы к adapter присваиваем MyMovieAdapter(
                adapter.notifyDataSetChanged()
                rvFilms.adapter = adapter                   //К нашему списку мы присоединяем adapter и присваиваем adapter.
            }
        })
    }

    override fun onSelect (films: Films){
        val bundle = Bundle()
        bundle.putSerializable("f", films.id)
        findNavController().navigate(R.id.action_top250_layout_to_title_250, bundle)
        Toast.makeText(requireContext(),films.fullTitle,Toast.LENGTH_LONG).show()
//        Log.i("test1", films.toString())

    }
}

//Layout Manager — это вещь, которая отвечает позиционирование View компонентов,
//которые больше не видны пользователю. Далее все также легко к нашему списку присоединяем layoutManager и уже к этому присваиваем
//layoutManager. Хорошо, теперь работа с библиотекой SpotsDialog. указываем ранее названную переменную с типом AlertDialog
//присваиваем SpotsDialog присоединяем метод Builder после этого присоединяем метод setCancelablec c параметром true к этому
//мы должны присоединить метод setContext c параметром this и присоединить метод build.
