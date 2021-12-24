package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapter.MyMovieAdapter
import com.example.myapplication.common.Common
import com.example.myapplication.databinding.FragmentTop250Binding
import com.example.myapplication.model.Films
import com.example.myapplication.retrofit.RetrofitServices
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import android.widget.ProgressBar
import com.example.myapplication.viewModel.MovieTop250ViewModel

class Top250 : Fragment(), OnFilmSelectListener {
    private val top250ViewModel: MovieTop250ViewModel by viewModels()
    lateinit var mService: RetrofitServices
    lateinit var adapter: MyMovieAdapter
    lateinit var numberTextView: TextView
    lateinit var endTime : TextView
    lateinit var layoutManager: LinearLayoutManager
    lateinit var rvFilms: RecyclerView
    private var _binding: FragmentTop250Binding? = null
    private val binding get() = _binding!!

//    private val viewModel: Top250ViewModel by viewModels()

//    Создаем переменные, и чтобы не объявлять их типа null объявим их через lateinit var

//  val adapter = Adapter { movie ->
//
//    }
//}
//  interface OnSelectLister {
//    fun onSelect(item: Films): Boolean
//}
//  class Adapter(listener: (Films) -> Boolean) {
//
//    init {
//        listener(ITEM)
//    }
//}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View ?{

        _binding = FragmentTop250Binding.inflate(inflater, container, false) // указываем с какой xml работаем
        return binding.root
    }//inflater - указывает с какой конткретно XML мы работаем

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val progressBar = view.findViewById(R.id.progressBar) as ProgressBar
        progressBar.visibility = ProgressBar.VISIBLE

        rvFilms = view.findViewById(R.id.list) //указываем что переменная rvFilms равна ID list
        numberTextView = view.findViewById(R.id.number_list)
        endTime = view.findViewById(R.id.time_list)
        mService = Common.retrofitService       //В методе onViewCreated мы к RetrofitServices присваиваем Common.retrofitServices.
        rvFilms.setHasFixedSize (true)  //recyclerView мы присоединяем  setHasFixedSize(true) благодаря этому методу мы сможем оптимизировать свой список
        layoutManager = LinearLayoutManager(context)
        rvFilms.layoutManager = layoutManager   // после мы к нашему layoutManager присваиваем LinearLayoutManager(context).

        viewLifecycleOwner.lifecycleScope.launch{
            top250ViewModel.resultMovie.collect {
                if (it != null){
                adapter = MyMovieAdapter(requireContext(), it,this@Top250) //requireContext возвращает ненулевое значение
                rvFilms.adapter = adapter

                    progressBar.visibility = ProgressBar.INVISIBLE

                    viewLifecycleOwner.lifecycleScope.launch{
                        top250ViewModel.numberList.collect {
                            numberTextView.text = it.toString()
                        }
                    }

                    viewLifecycleOwner.lifecycleScope.launch{
                        top250ViewModel.numberList.collect {
                            numberTextView.text = it.toString()
                        }
                    }

                    viewLifecycleOwner.lifecycleScope.launch{
                        top250ViewModel.timeList.collect {
                            endTime.text = it.toString()
                        }
                    }
                }
            }
        }
    }
//    private suspend fun getAllMovieList() {
//
//        kotlin.runCatching { withContext(Dispatchers.IO){
//            mService.getMovieList() } }
//            .onSuccess { response ->
//                adapter = MyMovieAdapter(requireContext(), response.items,this)
//                    rvFilms.adapter = adapter
//            }
//            .onFailure { e ->
//                Log.e("Response" , e.message,e)
//            }

//        mService.getMovieList().enqueue(object : Callback<Items> { // к mService добавляем метод getMovieList .enqueue object: Callback<MutableList>
//            override fun onFailure(call: Call<Items>, t: Throwable) {
//            }
//            //Предопределяем метод onResponse в с лучае получение данных
//            override fun onResponse(call: Call<Items>, response: Response<Items>){
//                val items = response.body()
//                adapter = MyMovieAdapter (context!!,items!!.items, this@Top250) //   // мы к adapter присваиваем MyMovieAdapter
//                adapter.notifyDataSetChanged()
//                rvFilms.adapter = adapter                   //К нашему списку мы присоединяем adapter и присваиваем adapter.
//            }
//        })
//    }

    override fun onSelect (films: Films){
        val bundle = Bundle()
        bundle.putSerializable("f", films.id) // засовываем в бандл ключ, и передаём ID
        findNavController().navigate(R.id.action_top250_layout_to_title_250, bundle)// указываем переход с одного фрагмента на другой
        Toast.makeText(requireContext(),films.fullTitle,Toast.LENGTH_LONG).show()  // фрагмент который будет высвечиваться при открытие второй страницы
//        Log.i("test1", films.toString())

    }
}
//getString
//Layout Manager — это вещь, которая отвечает позиционирование View компонентов,
//которые больше не видны пользователю. Далее все также легко к нашему списку присоединяем layoutManager и уже к этому присваиваем
//layoutManager. Хорошо, теперь работа с библиотекой SpotsDialog. указываем ранее названную переменную с типом AlertDialog
//присваиваем SpotsDialog присоединяем метод Builder после этого присоединяем метод setCancelablec c параметром true к этому
//мы должны присоединить метод setContext c параметром this и присоединить метод build.

//onAttach(Activity)
//Вызывается, когда фрагмент связывается с активностью. С этого момента мы можем получить ссылку на активность через метод getActivity()
//onCreate()
//В этом методе можно сделать работу, не связанную с интерфейсом. Например, подготовить адаптер.
//onCreateView(LayoutInflater, ViewGroup, Bundle)
//Вызывается для создания компонентов внутри фрагмента
//onViewCreated()
//Вызывается сразу после onCreateView()
//onActivityCreated(Bundle)
//Вызывается, когда отработает метод активности onCreate(), а значит фрагмент может обратиться к компонентам активности
//onStart()
//Вызывается, когда фрагмент видим для пользователя и сразу же срабатывает onStart() активности
//onDestroyView()
//onResume()
//Вызываем после onResume() активности
//Вызывается, когда набор компонентов удаляется из фрагмента
//onDetach()
//Вызывается, когда фрагмент отвязывается от активности.

//Корутины-Лёгкие потоки, представляют собой участок кода,
//выполнение которого может быть приостановленно,
//что бы выполнить какой либо другой участок кода,
//а затем вернуться и довыполнить тот который был приостановлен.


//линейный / реактивный подход

//val a = fun1()
//val b = fun2(object :)
//val c = b * b
//print(c)
//
//
//listOf(fun1())
//.map { a -> a * 2 }
//.map { b -> fun2(b) }
//.let { c ->
//    print(c)
//}

//        flowOf(1, 2, 3)
//            .map { it * it }
//            .collect { res -> print(res) }