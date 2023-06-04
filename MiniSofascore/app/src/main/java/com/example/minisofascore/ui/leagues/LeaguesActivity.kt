package com.example.minisofascore.ui.leagues

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.minisofascore.adapters.LeaguesRecyclerAdapter
import com.example.minisofascore.data.repositories.LeaguesRepository
import com.example.minisofascore.databinding.ActivityLeaguesBinding

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

        // Observe the leagues LiveData from the ViewModel
        observeViewModel()
    }

    private fun initRecyclerView() {
        leaguesRecyclerView = binding.leaguesRecyclerView
        leaguesRecyclerAdapter = LeaguesRecyclerAdapter()
        leaguesRecyclerView.adapter = leaguesRecyclerAdapter
    }

    private fun observeViewModel() {
        // Observe the leagues LiveData from the ViewModel
        viewModel.leagues.observe(this) { fetchedLeagues ->
            leaguesRecyclerAdapter.submitList(fetchedLeagues)
        }
    }
}
