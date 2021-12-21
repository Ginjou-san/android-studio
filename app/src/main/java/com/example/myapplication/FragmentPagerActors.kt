package com.example.myapplication

import Titles
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.adapter.*
import com.example.myapplication.viewModel.ActorsViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class FragmentViewPagerActors() : Fragment() {
    private val actorsViewModel: ActorsViewModel by viewModels()

    lateinit var rvActors: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
    //
        return inflater.inflate(R.layout.recycler_view_actors, container, false)
    }

    override fun onViewCreated(view: View,  savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

             // переменная которая ровна arguments, и достаем из бандл Titles
        rvActors = view.findViewById(R.id.recycler_actors)      //привязка фрагмента к адаптеру
        rvActors.layoutManager = LinearLayoutManager(context)   //
        rvActors.setHasFixedSize (true)                         //перременная имеет фиксированный размер ( все размеры будут одинаковые)


        viewLifecycleOwner.lifecycleScope.launch {
            actorsViewModel.resultActor.collect {
                if (it != null) {
                    rvActors.adapter = MyPagerActorsAdapter(it.actorList)
                }
            }
        }
    }
}


//layoutManager менеджер управление списком по управлению RecyclerView

//Context – это объект, который предоставляет доступ к базовым функциям приложения: доступ к ресурсам, к файловой системе, вызов активности и т.д.