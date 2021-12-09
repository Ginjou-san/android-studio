package com.example.myapplication

import Titles
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapter.*


class FragmentViewPagerActors : Fragment() {

    lateinit var rvActors: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.recycler_view_actors, container, false)
    }

    override fun onViewCreated(view: View,  savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val titleData = arguments?.getSerializable("t") as Titles

        rvActors = view.findViewById(R.id.recycler_actors)
        rvActors.layoutManager = LinearLayoutManager(context)
        rvActors.setHasFixedSize (true)
        rvActors.adapter = MyPagerActorsAdapter(titleData.actorList)
    }
}

