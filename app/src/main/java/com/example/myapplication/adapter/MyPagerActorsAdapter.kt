package com.example.myapplication.adapter

import Actor
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.*

class MyPagerActorsAdapter(private val actorList: List<Actor>) : RecyclerView.Adapter<MyPagerActorsAdapter.MyPagerActorsHolder>(){

    class MyPagerActorsHolder (itemView: View): RecyclerView.ViewHolder(itemView){

        val pagerImage: ImageView = itemView.findViewById(R.id.pager_image)
        val name: TextView = itemView.findViewById(R.id.pager_name)
        val pagerAsCharacter:TextView = itemView.findViewById(R.id.pager_as_character)

    fun bind(listItem: Actor){
        Glide.with(pagerImage.context).load(listItem.image).into (pagerImage)
        name.text = (name.context.getString(R.string.pager_name)+ " " + listItem.name)
        pagerAsCharacter.text = (pagerAsCharacter.context.getString(R.string.pager_as_character) + " " + listItem.asCharacter)
    }
}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPagerActorsHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.fragment_pager_actors,parent,false)

        return MyPagerActorsHolder(itemView)
    }

    override fun getItemCount() = actorList.size

    override fun onBindViewHolder(holder: MyPagerActorsHolder, position: Int) {
        val listItem = actorList[position]
        holder.bind(listItem)
    }

}