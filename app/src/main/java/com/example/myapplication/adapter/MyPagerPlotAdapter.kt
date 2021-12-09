package com.example.myapplication.adapter

import Titles
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.*


class MyPagerPlotAdapter (private val TitleList: List<Titles>):RecyclerView.Adapter<MyPagerPlotAdapter.MyPagerPlotAdapterHolder>() {

    class MyPagerPlotAdapterHolder (itemView: View): RecyclerView.ViewHolder(itemView){

        private val plot: TextView = itemView.findViewById(R.id.page_plot)

        fun bind(listItem: Titles){

            plot.text = (plot.context.getString(R.string.page_plot) + " " + listItem.plot)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPagerPlotAdapterHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.fragment_pager_plot,parent,false)

        return MyPagerPlotAdapterHolder(itemView)
    }

    override fun getItemCount() = TitleList.size

    override fun onBindViewHolder(holder: MyPagerPlotAdapterHolder, position: Int) {
        val listItem = TitleList[position]
        holder.bind(listItem)
    }
}
