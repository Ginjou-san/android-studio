package com.example.myapplication.adapter

import Titles
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapplication.FragmentPagePlot
import com.example.myapplication.FragmentViewPagerActors

class PageAdapter (fragment: Fragment, private val response: Titles) : FragmentStateAdapter(fragment) { //указываем в адаптаре что мы тут хотим увидить дата класс
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {      //пепеиспользуем функцию createFragment (создание объектов )
        when (position) {
            0 -> return FragmentViewPagerActors().apply {       //если позиция 0 мы возвращаем 1 фрагмент
                arguments = Bundle()
                arguments?.putSerializable("t", response)       //в бандл загружаем переменную  response, котоая в себе нечёт дата класс
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


//arguments