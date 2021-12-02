package com.example.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.Films

import com.example.myapplication.model.Titles


class MyTitleAdapter(private val context: Context, private val TitleList: List<Titles>, private val FilmsList: List<Films> ): RecyclerView.Adapter<MyTitleAdapter.MyTitleViewHolder>() {

    class MyTitleViewHolder (itemView: View):RecyclerView.ViewHolder(itemView){

        private val titles: TextView = itemView.findViewById(R.id.title_title)
//        private val image: ImageView = itemView.findViewById(R.id.title_image_movie)
//        private val imDbRating: TextView = itemView.findViewById(R.id.title_imDbRating)
//        private val genres: TextView = itemView.findViewById(R.id.title_genres)
//        private val year: TextView = itemView.findViewById(R.id.title_year)
//        private val director: TextView = itemView.findViewById(R.id.title_director)
//        private val metaCriticRating: TextView = itemView.findViewById(R.id.title_metaCriticRating)
//        private val liner: ImageView = itemView.findViewById(R.id.title_Liner)
//        private val star: TextView = itemView.findViewById(R.id.title_star_List)
//        private val boxOffice: TextView = itemView.findViewById(R.id.title_boxOffice)
//        private val plot: TextView = itemView.findViewById(R.id.title_plot)


        fun bind(listItem:Titles){
//            Glide.with(image.context).load(listItem.image).into (image)
//            Glide.with(liner.context).load(listItem.image).into (liner)
//            titles.text = ( titles.context.getString(R.string.title_title_text) + " " + listItem.title)
//            imDbRating.text = ( imDbRating.context.getString(R.string.title_imDbRating_text) + " " + listItem.imDbRating)
//            genres.text = (genres.context.getString(R.string.title_genres_text) + " " + listItem.genres)
//            year.text = (year.context.getString(R.string.year_text) + " " +  listItem.year)
//            director.text = (director.context.getString(R.string.title_director_text) + " " + listItem.directors)
//            metaCriticRating.text = ( metaCriticRating.context.getString(R.string.title_metacriticRating_text) + " " + listItem.metacriticRating)
//            star.text = ( star.context.getString(R.string.title_star_text) + " " + listItem.stars)
//            boxOffice.text = ( boxOffice.context.getString(R.string.title_boxOffice_text) + " " + listItem.boxOffice)
//            plot.text = ( plot.context.getString(R.string.title_plot_text) + " " + listItem.plot)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):MyTitleViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.fragment_title_top250,parent,false)
        return MyTitleViewHolder(itemView)

    }

    override fun getItemCount() = TitleList.size

    override fun onBindViewHolder(holder: MyTitleViewHolder, position: Int) {
        val listItem = TitleList[position]
        holder.bind(listItem)
    }



//    override fun getItemId(position: Int): Long {
//        return getItem(position).id
//    }
//
//    class ViewHolder(val binding: TitleCardBinding) : RecyclerView.ViewHolder(binding.root) {
//
//        init {
//            binding.root.setOnClickListener {
//                callback(getItem(layoutPosition), binding.cardView, binding.cardImage)
//            }
//        }
//
//        fun bind(titles: Titles) {
//            binding.cardView = titles
//        }
//    }

}
//
//class TitleCallback : DiffUtil.ItemCallback<Titles>() {
//
//    override fun areItemsTheSame(oldItem: Titles, newItem: Titles): Boolean {
//        return oldItem.id == newItem.id
//    }
//
//    override fun areContentsTheSame(oldItem: Titles, newItem: Titles): Boolean {
//        return oldItem == newItem
//    }
//
//}

//class TitlesAdapter(
//    private val callback: (titles: Titles, mcvPoster: MaterialCardView, textView: TextView) -> Unit
//) : ListAdapter<Titles, TitlesAdapter.ViewHolder>(TitleDiffCallback()) {
//
//    private var inflater: LayoutInflater? = null
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        if (inflater == null) {
//            inflater = LayoutInflater.from(parent.context)
//        }
//        val binding = TitleCardBinding.inflate(inflater!!, parent, false)
//        return ViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.bind(getItem(position))
//    }
//
//    override fun getItemId(position: Int): Long {
//        return getItem(position).id
//    }
//
//    inner class ViewHolder(val binding: TitleCardBinding) : RecyclerView.ViewHolder(binding.root) {
//
//        init {
//            binding.root.setOnClickListener {
//                callback(getItem(layoutPosition), binding.cardView, binding.cardImage)
//            }
//        }
//
//        fun bind(titles: Titles) {
//            binding.cardView = titles
//        }
//    }
//
//}
//
//class TitleDiffCallback : DiffUtil.ItemCallback<Titles>() {
//
//    override fun areItemsTheSame(oldItem: Titles, newItem: Titles): Boolean {
//        return oldItem.id == newItem.id
//    }
//
//    override fun areContentsTheSame(oldItem: Titles, newItem: Titles): Boolean {
//        return oldItem == newItem
//    }
//
//}