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
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.myapplication.adapter.MyTitleAdapter
import com.example.myapplication.adapter.PageAdapter
import com.example.myapplication.common.Common
import com.example.myapplication.retrofit.RetrofitServices
import com.example.myapplication.viewModel.ActorsViewModel
import com.example.myapplication.viewModel.PlotViewModel
import com.example.myapplication.viewModel.TitleTop250ViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class TitleTop250 : Fragment() {

    private lateinit var adapterPage: PageAdapter
    private lateinit var viewPager:ViewPager2
    private lateinit var tabLayout: TabLayout
    private val titleTop250ViewModel: TitleTop250ViewModel by viewModels()
//    private val actorsViewModel: ActorsViewModel by viewModels()
//    private val PlotViewModel: PlotViewModel by viewModels()
    var tabTitle = arrayOf("Actors","Plot")
//    lateinit var titleData:Titles
    lateinit var mService: RetrofitServices
    lateinit var tvTitles: TextView
    lateinit var tvRating:TextView
    lateinit var tvGenres:TextView
    lateinit var tvYear:TextView
    lateinit var tvDirector:TextView
    lateinit var tvMetaCriticRating:TextView
    lateinit var tvBoxOffice:TextView
    lateinit var adapter: MyTitleAdapter
    lateinit var rvFilms: RecyclerView
    lateinit var tvImageMovies:ImageView

    //Создаем переменные с которых будем ссылаться на Id, и чтобы не объявлять их типа null объявим их через lateinit var

    override fun onCreateView(  //onCreate – он вызывается, когда приложение создает и отображает Activity
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        return inflater.inflate(R.layout.fragment_title_top250, container, false) // даем возможность работать с полями через inflater
    }   //inflater - указывает с какой конткретно XML мы работаем

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(
            view,
            savedInstanceState
        ) //это вызов метода родительского класса, выполняющий необходимые процедуры.

        val horizontalScrollView = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )// в этой переменной укахываем чтоб horizontalScrollView был горизонтальным

        viewPager = view.findViewById(R.id.page)
        tabLayout = view.findViewById(R.id.tab_layout)
        rvFilms = view.findViewById(R.id.title_Liner)   //указываем что переменная rvFilms  равна  id title_Liner
        mService = Common.retrofitService               //В методе onViewCreated к RetrofitServices присваиваем Common.retrofitServices.
        rvFilms.layoutManager = horizontalScrollView
        tvTitles = view.findViewById(R.id.title_title)
        tvRating = view.findViewById(R.id.title_imDbRating)
        tvGenres = view.findViewById(R.id.title_genres)
        tvYear = view.findViewById(R.id.title_year)
        tvDirector = view.findViewById(R.id.title_director)
        tvMetaCriticRating = view.findViewById(R.id.title_metaCriticRating)
        tvBoxOffice = view.findViewById(R.id.title_boxOffice)
        tvImageMovies = view.findViewById(R.id.title_image_movie)

        //связываем наши переменные с ID
//        val dataId = arguments?.getSerializable("f") as String


        viewLifecycleOwner.lifecycleScope.launch { //привязываем корутину, к жизненному циклу фрагмента (view)

            titleTop250ViewModel.resultTitles.collect {
                if (it != null) {

//                    rvFilms.adapter = MyTitleAdapter(requireContext(),it.images.items)
//                    titleTop250ViewModel.load(dataId)
                    adapter = MyTitleAdapter(requireContext(),it.images.items)
                    rvFilms.adapter = adapter


                    ui(it)

                    adapterPage = PageAdapter(this@TitleTop250, it)
                    viewPager.adapter = adapterPage

                    TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                        tab.text = tabTitle[position]
                    }.attach()
                }
            }

        }
//        titleTop250ViewModel.load(dataId)

    }
//    private suspend fun getAllMovieList() {         // даём котлину понять, что это suspend функция и она будет приостанавливать корутину.
//        val dataId = arguments?.getSerializable("t") as String  // arguments? вытаскиваем из бандла как строку
//
//        kotlin.runCatching {
//            withContext(Dispatchers.IO) {//переключаем контекст текущей сопрограммы, когда выполняется данный блок, сопрограмма переключается обратно в предыдущий контекст.Использует общий пул потоков, для ввода и вывода
//                mService.getTitleList(dataId)
//            }
//        } // указываем что у mService аргумент, getTitleList. И ссылаемся на ID
//            .onSuccess { TestResponse ->      //выполняем данное действие, если будет успех
//                adapter = MyTitleAdapter(requireContext(),TestResponse.images.items) //указываю аргумент
//                rvFilms.adapter = adapter
//
//                ui(TestResponse)
//                adapterPage = PageAdapter(this, TestResponse)
//                viewPager.adapter = adapterPage
//
//                TabLayoutMediator(tabLayout, viewPager) { tab, position ->
//                    tab.text = tabTitle[position]
//                }.attach()
//            }
//            .onFailure { e ->                    //выполняем данное действие, если будет ошибка
//                Log.e("Response", e.message, e)
//            }
//    }
//        val titleId = arguments?.getSerializable("f") as String        // arguments? вытаскиваем из бандла как строку
//        mService.getTitleList(titleId).enqueue(object : Callback<Titles>{  // к mService добавляем метод getMovieList .enqueue object: Callback<MutableList>
//            override fun onFailure(call: Call<Titles>, t: Throwable) {
//                Log.i("test1", t.toString())
//            }
//            //Предопределяем метод onResponse в с лучае получение данных
//            override fun onResponse(call: Call<Titles>, response: Response<Titles>) {
//                val titlesItems = response.body()
//                if (titlesItems !=null) {
//                    titleData = titlesItems
//                }
//                if (titlesItems == null){
//                    return
//                }
//
//                adapterPage = PageAdapter (this@TitleTop250,titleData) // В Адаптер засовываем дата класс
//                viewPager.adapter = adapterPage
//
//                TabLayoutMediator(tabLayout, viewPager) { //показываем с чем нужно работать медиатру
//                        tab, position ->                //указываем в какой последовательности вставлять фразменты
//                    tab.text = tabTitle[position]       //указываем откуда брать название вкладок
//                }.attach()
//
//                ui(titlesItems)
//
//                adapter = MyTitleAdapter (context!!, titlesItems.images.items)
//                adapter.notifyDataSetChanged()
//                rvFilms.adapter = adapter
//
//            }
//        })
//    }

    private fun ui(titles: Titles) {
        tvTitles.text = titles.title
        tvRating.text = titles.imDbRating
        tvGenres.text = titles.genres
        tvYear.text = titles.year
        tvDirector.text = titles.directors
        tvMetaCriticRating.text = titles.metacriticRating
        tvBoxOffice.text = titles.boxOffice.cumulativeWorldwideGross
        Glide.with(tvImageMovies.context).load(titles.image).into(tvImageMovies)
    }
        //связываем наши переменные с Data class
}


