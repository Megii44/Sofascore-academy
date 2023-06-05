package com.example.minisofascore.ui.leagues

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.minisofascore.adapters.LeaguesRecyclerAdapter
import com.example.minisofascore.data.enums.SportEnum
import com.example.minisofascore.data.repositories.LeaguesRepository
import com.example.minisofascore.databinding.ActivityLeaguesBinding
import com.google.android.material.tabs.TabLayout

class LeaguesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLeaguesBinding
    private lateinit var viewModel: LeaguesViewModel
    private lateinit var leaguesRecyclerView: RecyclerView
    private lateinit var leaguesRecyclerAdapter: LeaguesRecyclerAdapter

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

        // Initialize RecyclerView
        initRecyclerView()

        // Setup tabs
        setupTabs()

        // Observe the leagues LiveData from the ViewModel
        observeViewModel()
    }

    private fun initRecyclerView() {
        leaguesRecyclerView = binding.leaguesRecyclerView
        leaguesRecyclerAdapter = LeaguesRecyclerAdapter()
        leaguesRecyclerView.adapter = leaguesRecyclerAdapter
    }

    private fun setupTabs() {
        val tabs: TabLayout = binding.tabs

        // Set the tab titles
        tabs.addTab(tabs.newTab().setText(SportEnum.Football.toString()))
        tabs.addTab(tabs.newTab().setText(SportEnum.Basketball.toString()))
        tabs.addTab(tabs.newTab().setText(SportEnum.AmericanFootball.toString()))

        // Fetch leagues for the selected sport in the tab
        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    val sport = when (it.position) {
                        0 -> SportEnum.Football.toString().lowercase()
                        1 -> SportEnum.Basketball.toString().lowercase()
                        2 -> SportEnum.AmericanFootball.toString().lowercase()
                        else -> SportEnum.Football.toString().lowercase() // Handle the case when the index is out of bounds
                    }
                    viewModel.fetchLeagues(sport)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        // Set the initial selected tab
        tabs.selectTab(tabs.getTabAt(0))
    }

    private fun observeViewModel() {
        // Observe the leagues LiveData from the ViewModel
        viewModel.leagues.observe(this) { fetchedLeagues ->
            leaguesRecyclerAdapter.submitList(fetchedLeagues)
        }
    }
}
