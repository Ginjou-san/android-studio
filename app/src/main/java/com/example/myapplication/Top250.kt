package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapter.MyMovieAdapter
import com.example.myapplication.common.Common
import com.example.myapplication.databinding.FragmentTop250Binding
import com.example.myapplication.data.FilmsDatabase
import com.example.myapplication.data.FilmsDatabase.Companion.getDatabase
import com.example.myapplication.data.FilmsId
import com.example.myapplication.model.FavoriteList
import com.example.myapplication.model.Films
import com.example.myapplication.retrofit.RetrofitServices
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import com.example.myapplication.viewModel.MovieTop250ViewModel
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Top250 : Fragment(), OnFilmSelectListener {
    private val top250ViewModel: MovieTop250ViewModel by viewModels()
    private var _binding: FragmentTop250Binding? = null
    private val binding get() = _binding!!
    lateinit var mService: RetrofitServices
    lateinit var adapter: MyMovieAdapter
    lateinit var numberTextView: TextView
    lateinit var endTime: TextView
    lateinit var layoutManager: LinearLayoutManager
    lateinit var rvFilms: RecyclerView
    lateinit var editText: EditText
    lateinit var searchButton: ImageButton
    lateinit var addBase: FilmsDatabase
    lateinit var favoritesButton: MaterialButton
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
    ): View {

        //        setupActionBarWithNavController(findNavController(R.id.film_iteam))
        _binding = FragmentTop250Binding.inflate(
            inflater,
            container,
            false
        ) // указываем с какой xml работаем
        return binding.root
    }//inflater - указывает с какой конткретно XML мы работаем

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val progressBar = view.findViewById(R.id.progressBar) as ProgressBar
        progressBar.visibility = ProgressBar.VISIBLE

        addBase = context?.let { getDatabase(it) }!!
        favoritesButton = view.findViewById(R.id.favorites)
        searchButton = view.findViewById(R.id.search_button)
        editText = view.findViewById(R.id.edit_text)
        rvFilms = view.findViewById(R.id.list) //указываем что переменная rvFilms равна ID list
        numberTextView = view.findViewById(R.id.number_list)
        endTime = view.findViewById(R.id.time_list)
        mService =
            Common.retrofitService       //В методе onViewCreated мы к RetrofitServices присваиваем Common.retrofitServices.
        rvFilms.setHasFixedSize(true)  //recyclerView мы присоединяем  setHasFixedSize(true) благодаря этому методу мы сможем оптимизировать свой список
        layoutManager = LinearLayoutManager(context)
        rvFilms.layoutManager =
            layoutManager   // после мы к нашему layoutManager присваиваем LinearLayoutManager(context).

        adapter = MyMovieAdapter(
            requireContext(), this@Top250
        ) //requireContext возвращает ненулевое значение
        rvFilms.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {//viewLifecycleOwner - нужден для инициализации самой корутины.lifecycleScope - привязываем корутину к жизненнему циклу фрагмента.
            top250ViewModel.resultMovie.collect {
                if (it != null) {
                    adapter.movieList = it
                    adapter.notifyDataSetChanged()//уведомляю что набор данных изменился

                    progressBar.visibility = ProgressBar.INVISIBLE

                    viewLifecycleOwner.lifecycleScope.launch {
                        top250ViewModel.numberList.collect {
                            numberTextView.text = it.toString()
                        }
                    }
                    viewLifecycleOwner.lifecycleScope.launch {
                        top250ViewModel.timeList.collect {
                            endTime.text = it.toString()
                        }
                    }
                }
            }
        }
        searchButton.setOnClickListener {
            editText.setText("")
        }
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                updateSearch()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }
    private fun updateSearch() {
        val s = editText.text.toString()

        if (s.isEmpty()) {
            top250ViewModel.clear()
            searchButton.visibility = View.GONE
        } else {
            top250ViewModel.search(s)
            searchButton.visibility = View.VISIBLE
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
    override fun onSelect(films: Films) {//показываем что она принимает
        val bundle = Bundle()
        bundle.putSerializable("d", films.id) // засовываем в бандл ключ, и передаём ID
        findNavController().navigate(
            R.id.action_top250_layout_to_title_250,
            bundle
        )// указываем переход с одного фрагмента на другой
        Toast.makeText(requireContext(), films.fullTitle, Toast.LENGTH_LONG)
            .show()  // фрагмент который будет высвечиваться при открытие второй страницы
//        Log.i("test1", films.toString())
    }
    override fun onBase(film: Films) {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {  //запускаеться как только содаеться
            withContext(Dispatchers.IO) {                     //Передаем контекст  корутине. В Dispatchers указываем в каком потоке будет выполняеться.
                addBase.daoFilms().insert(FilmsId(film.id))   //возвращаем в dao FilmsId
            }
        }
    }

    override fun onFavorite(film: Films) {
        top250ViewModel.onFavorite(film)
    }

    override fun insert(film: Films){
        top250ViewModel.insert(film)
    }

    override fun onDelete(film: Films) {
        top250ViewModel.delete(film)
    }
}
//    override fun onFavorigte(film: Films) {
//        viewLifecycleOwner.lifecycleScope.launch {//запускаеться как только к ней обратимся
//            withContext(Dispatchers.IO) {
//                val loadFilm: List<FavoriteList> = addBase.daoFilms().load(FilmsId(film.id))
//                top250ViewModel.favorite(loadFilm)
//
//            }
//        }
//    }


//override fun onDelete(film: Films) {
//    viewLifecycleOwner.lifecycleScope.launchWhenCreated {//запускаеться как только содаеться
//        withContext(Dispatchers.IO) {
//            addBase.daoFilms().delete(FilmsId(film.id))
//        }
//    }
//}
//По умолчанию построитель корутин launch создает и сразу же запускает корутину.
//whenCreated - MSDN. Назначение: в этом атрибуте хранится значение времени создания всех объектов

//withContext(context) : переключает контекст текущей сопрограммы, когда выполняется данный блок, сопрограмма переключается обратно в предыдущий контекст.

// launch — метод для асинхронного запуска корутины. Соответственно, метод longRunningMethod() запустится сразу же.
// Метод возвращает экземпляр класса Job. Этот объект можно использовать для того, чтобы, например, отменить корутину — job.cancel().
// Альтернатива — метод asunc(). Он вернет Deferred<T> — отложенную корутину, которую можно запустить позднее.

// Dispatchers.IO — один из параметров метода launch(). Здесь указывается диспетчер для созданной корутины.
// Конкретно диспетчер Dispatchers.IO используется для фоновых задач, не блокирующих основной поток.
// Если указать Dispatchers.Main, то корутина будет выполняться в основном потоке.

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