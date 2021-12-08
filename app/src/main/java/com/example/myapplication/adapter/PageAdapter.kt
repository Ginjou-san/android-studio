package com.example.myapplication.adapter

import Actor
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapplication.ActorList
import com.example.myapplication.FragmentPagePlot
import com.example.myapplication.FragmentViewPagerActors

    private var tvName = String()
    private var tvImages = String()
    private var tvCharacter = String()

class PageAdapter (fragment: Fragment) : FragmentStateAdapter(fragment), ActorList {
    override fun getItemCount(): Int {
        return  2
    }

    override fun createFragment(position: Int): Fragment {
        when ( position) {
            0 -> return FragmentViewPagerActors ().apply {
                arguments = Bundle()
                arguments?.putString("t", tvName)
                arguments?.putString("t", tvImages)
                arguments?.putString("t", tvCharacter)
            }
            1 -> return FragmentPagePlot().apply {
//                arguments = Bundle()
            }
            else -> return FragmentViewPagerActors()
        }
    }

    override fun onSelect(actorList: Actor) {

        val name = actorList.name
        tvName = name
        val images = actorList.image
        tvImages = images
        val character = actorList.asCharacter
        tvCharacter = character
    }
}

//Функция apply работает почти так же, как with, но возвращает объект, переданный в аргументе.
// Полезна в тех случаях, когда требуется создание экземпляра, у которого следует инициализировать некоторые свойства.
// Часто в этих случаях мы просто повторяем имя экземпляра. Инициализируем настройки кнопки через apply.
