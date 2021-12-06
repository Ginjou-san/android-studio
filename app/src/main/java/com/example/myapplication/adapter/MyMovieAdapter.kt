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
    //В классе MyMovieAdapter мы создаем переменные, которые будут доступны только
//в этом классе (private val movieList: List типа Films) и указываем тип возвращаемого значения

    class MyViewHolder (itemView: View, val listener: OnFilmSelectListener):RecyclerView.ViewHolder(itemView){

        private val image: ImageView = itemView.findViewById(R.id.image_movie)
        private val rank: TextView = itemView.findViewById(R.id.rank)
        private val title: TextView = itemView.findViewById(R.id.title)
        private val fullTitle: TextView = itemView.findViewById(R.id.fullTitle)
        private val year: TextView = itemView.findViewById(R.id.year)
        private val crew: TextView = itemView.findViewById(R.id.crew)
        private val imDbRating:TextView = itemView.findViewById(R.id.imDbRating)

        //создаем переменные и приравниваем из к ID из XML

        fun bind(listItem:Films){ //Создам функцию bind c параметром listItem: Films, здесь мы передаём данные с Data class в переменные, а сами переменные уже ссылаются на ID в XML
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
        //Создаем переменную itemView присваиваем ей LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        //и возвращаем MyViewHolder с параметром itemView.

        //val image:ImageView = itemView.image_movie image_movie у нас тянется из item_layout
        //и так указываем для всех оставшихся view элементов.
    return MyViewHolder(itemView, listener)
    }

    override fun getItemCount() = movieList.size //Далее мы переделываем getItemCount() в override fun getItemCount() = movieList.size.
    //Здесь все просто, мы создали функцию и возвращаем movieList.size.
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val listItem = movieList[position] //в теле мы создаем переменную listItem: Movie и присваиваем movieList[position].
        holder.bind(listItem) //Далее к holder мы присоединяем метод bind с параметрами listItem.
    }
}
