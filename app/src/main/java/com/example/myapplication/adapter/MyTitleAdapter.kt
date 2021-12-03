package com.example.myapplication.adapter

import Item
import Titles
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.model.Films



class MyTitleAdapter(private val context: Context, private val TitleList: List<Item>): RecyclerView.Adapter<MyTitleAdapter.MyTitleViewHolder>() {

    class MyTitleViewHolder (itemView: View):RecyclerView.ViewHolder(itemView){

         val screenshotTitle: ImageView = itemView.findViewById(R.id.card_image)


        fun bind(listItem: Item){
            Glide.with(screenshotTitle.context).load(listItem.image).into (screenshotTitle)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):MyTitleViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.title_card,parent,false)
        return MyTitleViewHolder(itemView)

    }

    override fun getItemCount() = TitleList.size

    override fun onBindViewHolder(holder: MyTitleViewHolder, position: Int) {
        val listItem = TitleList[position]
        holder.bind(listItem)
    }

}
