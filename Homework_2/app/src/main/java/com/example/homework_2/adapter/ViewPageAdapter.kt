package com.example.homework_2.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.homework_2.ui.home.AddProductFragment
import com.example.homework_2.ui.products.ProductsFragment

private const val NUM_TABS = 2

public class ViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        return if (position == 0)
            AddProductFragment()
        else
            ProductsFragment()
    }
}