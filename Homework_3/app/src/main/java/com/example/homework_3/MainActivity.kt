package com.example.homework_3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.homework_3.adapter.ViewPagerAdapter
import com.example.homework_3.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2
    private lateinit var adapter: ViewPagerAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        tabLayout = binding.tabLayout
        viewPager2 = binding.viewPager2

        adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)

        val homeTab = tabLayout.newTab()
        homeTab.setIcon(R.drawable.ic_add_products)
        homeTab.setText(R.string.home)
        tabLayout.addTab(homeTab)

        val productsTab = tabLayout.newTab()
        productsTab.setIcon(R.drawable.ic_products)
        productsTab.setText(R.string.products)
        tabLayout.addTab(productsTab)

        val settingsTab = tabLayout.newTab()
        productsTab.setIcon(R.drawable.ic_settings)
        productsTab.setText(R.string.settings)
        tabLayout.addTab(settingsTab)

        viewPager2.adapter = adapter

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if(tab != null) {
                    viewPager2.currentItem = tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })

        viewPager2.registerOnPageChangeCallback((object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        }))
    }
}