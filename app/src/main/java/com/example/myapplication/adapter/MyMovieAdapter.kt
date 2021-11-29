package com.example.myapplication.adapter

import android.annotation.SuppressLint
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

        private val image: ImageView = itemView.findViewById(R.id.image_movie)
        private val id: TextView = itemView.findViewById(R.id.id)
        private val rank: TextView = itemView.findViewById(R.id.rank)
        private val title: TextView = itemView.findViewById(R.id.title)
        private val fullTitle: TextView = itemView.findViewById(R.id.fullTitle)
        private val year: TextView = itemView.findViewById(R.id.year)
        private val crew: TextView = itemView.findViewById(R.id.crew)
        private val imDbRating:TextView = itemView.findViewById(R.id.imDbRating)
        private val imDbRatingCount: TextView = itemView.findViewById(R.id.imDbRatingCount)
        private val test1 = R.string.id_text





        fun bind(listItem:Films){
            Glide.with(image.context).load(listItem.image).into (image)
            id.text = ( "$test1" + listItem.id)
            rank.text = ("rank: " + listItem.rank.toString())
            title.text = ("title: " + listItem.title)
            fullTitle.text = ("fullTitle: " + listItem.fullTitle)
            year.text = ("year: " +  listItem.year.toString())
            crew.text = ( "crew: " + listItem.crew)
            imDbRating.text = ( "imDbRating: " +  listItem.imDbRating.toString())
            imDbRatingCount.text = ( "imDbRatingCount: " + listItem.imDbRatingCount.toString())
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


