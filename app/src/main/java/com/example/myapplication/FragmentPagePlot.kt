package com.example.myapplication

import Titles
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapter.MyPagerActorsAdapter
import com.example.myapplication.viewModel.ActorsViewModel
import com.example.myapplication.viewModel.PlotViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class FragmentPagePlot : Fragment() {

    lateinit var rvPlot: TextView
    private val plotViewModel: PlotViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pager_plot, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvPlot = view.findViewById(R.id.page_plot)
//       rvPlot.text = titleData.plot


        viewLifecycleOwner.lifecycleScope.launch {
            plotViewModel.resultPlot.collect {
                if (it != null) {
                    rvPlot.text = it.plot //интеграция текста ( что бы менять текст, если он поменяеться)
                }
            }
        }
    }
}