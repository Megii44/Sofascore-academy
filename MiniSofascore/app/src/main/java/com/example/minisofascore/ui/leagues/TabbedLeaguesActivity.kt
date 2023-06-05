package com.example.minisofascore.ui.leagues

import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.minisofascore.adapters.LeaguesSectionsPagerAdapter
import com.example.minisofascore.databinding.ActivityTabbedLeaguesBinding
import com.google.android.material.tabs.TabLayoutMediator

class TabbedLeaguesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTabbedLeaguesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTabbedLeaguesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val leaguesSectionsPagerAdapter = LeaguesSectionsPagerAdapter(this, supportFragmentManager, lifecycle)
        val viewPager: ViewPager2 = binding.viewPager
        viewPager.adapter = leaguesSectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = leaguesSectionsPagerAdapter.getPageTitle(position)
        }.attach()
    }
}