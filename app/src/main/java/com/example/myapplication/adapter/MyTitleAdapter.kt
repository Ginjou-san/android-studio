package com.example.myapplication.adapter

import Item
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R



class MyTitleAdapter(private val context: Context, private val TitleList: List<Item>): RecyclerView.Adapter<MyTitleAdapter.MyTitleViewHolder>() {
    //В классе MyTitleAdapter мы создаем переменные, которые будут доступны только
//в этом классе (private val TitleList: List типа Item и указываем) тип возвращаемого значения, то что мы ждём получить
    class MyTitleViewHolder (itemView: View):RecyclerView.ViewHolder(itemView){

         val screenshotTitle: ImageView = itemView.findViewById(R.id.card_image)

        //создаем переменные и приравниваем из к ID из XML


        fun bind(listItem: Item){ // здесь мы передаём данные с Data class в переменные, а сами переменные уже ссылаются на ID в XML
            Glide.with(screenshotTitle.context).load(listItem.image).into (screenshotTitle) // через Glide мы передаем изображения

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):MyTitleViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.title_card,parent,false)
        //Создаем переменную itemView присваиваем ей LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        //и возвращаем MyTitleViewHolder с параметром itemView.
        return MyTitleViewHolder(itemView)

    }

    override fun getItemCount() = TitleList.size
    //Далее мы переделываем getItemCount() в override fun getItemCount() = TitleList.size.
    //Здесь все просто, мы создали функцию и возвращаем movieList.size.
    override fun onBindViewHolder(holder: MyTitleViewHolder, position: Int) {
        val listItem = TitleList[position] //в теле мы создаем переменную TitleList: Movie и присваиваем movieList[position].
        holder.bind(listItem)//Далее к holder мы присоединяем метод bind с параметрами listItem.
    }

}
