package com.example.myapplication.adapter

import android.app.LauncherActivity
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


class MyMovieAdapter(private val context: Context,private val movieList: List<Films>):RecyclerView.Adapter<MyMovieAdapter.MyViewHolder>() {

    class MyViewHolder (itemView: View):RecyclerView.ViewHolder(itemView){

        val image: ImageView = itemView.findViewById(R.id.image_movie)
        val id: TextView = itemView.findViewById(R.id.id)
        val rank: TextView = itemView.findViewById(R.id.rank)
        val title: TextView = itemView.findViewById(R.id.title)
        val fullTitle: TextView = itemView.findViewById(R.id.fullTitle)
        val year: TextView = itemView.findViewById(R.id.year)
        val crew: TextView = itemView.findViewById(R.id.crew)
        val imDbRating:TextView = itemView.findViewById(R.id.imDbRating)
        val imDbRatingCount: TextView = itemView.findViewById(R.id.imDbRatingCount)



        fun bind(listItem:Films){
            Glide.with(image.context).load(listItem.image).into (image)
            id.text = listItem.id
            rank.text = listItem.rank.toString()
            title.text = listItem.title
            fullTitle.text = listItem.fullTitle
            year.text = listItem.year.toString()
            crew.text = listItem.crew
            imDbRating.text = listItem.imDbRating.toString()
            imDbRatingCount.text = listItem.imDbRatingCount.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false)
    return MyViewHolder(itemView)
    }

    override fun getItemCount() = movieList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val listItem = movieList[position]
        holder.bind(listItem)
    }
}


