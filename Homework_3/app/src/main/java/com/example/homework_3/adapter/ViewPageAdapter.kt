package com.example.homework_3.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.homework_3.ui.home.AddProductFragment
import com.example.homework_3.ui.products.ProductsFragment
import com.example.homework_3.ui.settings.SettingsFragment

private const val NUM_TABS = 3

class ViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> AddProductFragment()
            1 -> {
                ProductsFragment()
            }
            else -> {
                SettingsFragment()
            }
        }
    }
}