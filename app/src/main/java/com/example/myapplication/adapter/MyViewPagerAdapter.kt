//package com.example.myapplication.adapter
//
//import Item
//import android.view.View
//import android.widget.ImageView
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.FragmentStatePagerAdapter
//import androidx.recyclerview.widget.RecyclerView
//import androidx.viewpager2.adapter.FragmentStateAdapter
//import com.bumptech.glide.Glide
//import com.example.myapplication.FragmentViewPager
//import com.example.myapplication.R
//
//class MyViewPagerAdapter (private val TitleList: List<Item>,fragmentViewPager: FragmentViewPager ) : FragmentStateAdapter(fragmentViewPager) {
//
//    class MyViewPagerHolder (itemView: View): RecyclerView.ViewHolder(itemView){
//
//
//    val screenshotTitle: ImageView = itemView.findViewById(R.id.card_image)
//
//
//
//    fun bind(listItem: Item){
//        Glide.with(screenshotTitle.context).load(listItem.image).into (screenshotTitle)
//    }
//}
//    override fun getItemCount(): Int {
//
//
//    }
//}