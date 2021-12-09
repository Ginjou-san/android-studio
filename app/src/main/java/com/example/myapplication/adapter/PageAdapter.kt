package com.example.myapplication.adapter

import Actor
import Titles
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapplication.FragmentPagePlot
import com.example.myapplication.FragmentViewPagerActors






class PageAdapter (fragment: Fragment, private val response: Titles) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return FragmentViewPagerActors().apply {
                arguments = Bundle()
                arguments?.putSerializable("t", response)
            }
            1 -> return FragmentPagePlot().apply {
                arguments = Bundle()
                arguments?.putSerializable("t",response)
            }
            else -> return FragmentViewPagerActors()
        }
    }
}
//Функция with позволяет выполнить несколько операций над одним объектом, не повторяя его имени.
//Функция apply работает почти так же, как with, но возвращает объект, переданный в аргументе.
// Полезна в тех случаях, когда требуется создание экземпляра, у которого следует инициализировать некоторые свойства.
// Часто в этих случаях мы просто повторяем имя экземпляра. Инициализируем настройки кнопки через apply.
