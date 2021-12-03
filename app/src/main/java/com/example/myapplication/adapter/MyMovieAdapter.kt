package com.example.myapplication.adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.OnFilmSelectListener
import com.example.myapplication.R
import com.example.myapplication.model.Films


class MyMovieAdapter(private val context: Context,private val movieList: List<Films>, private val listener: OnFilmSelectListener):RecyclerView.Adapter<MyMovieAdapter.MyViewHolder>() {

    class MyViewHolder (itemView: View, val listener: OnFilmSelectListener):RecyclerView.ViewHolder(itemView){

        private val image: ImageView = itemView.findViewById(R.id.image_movie)
        private val rank: TextView = itemView.findViewById(R.id.rank)
        private val title: TextView = itemView.findViewById(R.id.title)
        private val fullTitle: TextView = itemView.findViewById(R.id.fullTitle)
        private val year: TextView = itemView.findViewById(R.id.year)
        private val crew: TextView = itemView.findViewById(R.id.crew)
        private val imDbRating:TextView = itemView.findViewById(R.id.imDbRating)


        fun bind(listItem:Films){
            Glide.with(image.context).load(listItem.image).into (image)
            rank.text = (rank.context.getString(R.string.rank_text) + " " + listItem.rank.toString())
            title.text = (title.context.getString(R.string.title_text) + " " +  listItem.title)
            fullTitle.text = (fullTitle.context.getString(R.string.fullTitle_text) + " " + listItem.fullTitle)
            year.text = (year.context.getString(R.string.year_text) + " " + listItem.year.toString())
            crew.text = ( crew.context.getString(R.string.crew_text) + " " + listItem.crew)
            imDbRating.text = ( imDbRating.context.getString(R.string.imDbRating_text) + " " + listItem.imDbRating.toString())
            itemView.setOnClickListener { listener.onSelect(listItem) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.film_iteam,parent,false)
    return MyViewHolder(itemView, listener)
    }

    override fun getItemCount() = movieList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val listItem = movieList[position]
        holder.bind(listItem)
    }
}


