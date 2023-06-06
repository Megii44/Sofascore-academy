package com.example.minisofascore.ui.leagues

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.minisofascore.adapters.LeaguesSectionsPagerAdapter
import com.example.minisofascore.data.enums.SportEnum
import com.example.minisofascore.data.repositories.LeaguesRepository
import com.example.minisofascore.databinding.ActivityLeaguesBinding
import com.google.android.material.tabs.TabLayoutMediator

class LeaguesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLeaguesBinding
    private lateinit var viewModel: LeaguesViewModel
    private lateinit var leaguesPagerAdapter: LeaguesSectionsPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout for this activity
        binding = ActivityLeaguesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Create the repository instance
        val leaguesRepository = LeaguesRepository()

        // Initialize the ViewModel using the custom factory
        val viewModelFactory = LeaguesViewModelFactory(leaguesRepository)
        viewModel = ViewModelProvider(this, viewModelFactory)[LeaguesViewModel::class.java]

        // Initialize ViewPager2
        initViewPager()
    }

    private fun initViewPager() {
        // Initialize ViewPager2
        leaguesPagerAdapter = LeaguesSectionsPagerAdapter(this, supportFragmentManager, lifecycle)
        binding.viewPager.adapter = leaguesPagerAdapter

        // Connect TabLayout with ViewPager2
        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> SportEnum.Football.toString()
                1 -> SportEnum.Basketball.toString()
                2 -> SportEnum.AmericanFootball.toString()
                else -> ""
            }
        }.attach()
    }
}
