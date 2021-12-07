package com.example.myapplication.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapplication.FragmentPagePlot
import com.example.myapplication.FragmentViewPagerActors

class TabLayoutAdapter (fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return  2
    }

    override fun createFragment(position: Int): Fragment {
        when ( position) {
            0 -> return FragmentViewPagerActors ()
            1 -> return FragmentPagePlot()
            else -> return FragmentViewPagerActors()

        }
    }


}