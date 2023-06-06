package com.example.minisofascore.ui.leagues

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.minisofascore.adapters.LeaguesRecyclerAdapter
import com.example.minisofascore.data.repositories.LeaguesRepository
import com.example.minisofascore.databinding.FragmentLeaguesBinding

class LeaguesFragment : Fragment() {
    private lateinit var binding: FragmentLeaguesBinding
    private lateinit var viewModel: LeaguesViewModel
    private lateinit var leaguesAdapter: LeaguesRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLeaguesBinding.inflate(inflater, container, false)

        // Initialize the adapter
        leaguesAdapter = LeaguesRecyclerAdapter()

        // Set up RecyclerView
        binding.leaguesRecyclerView.adapter = leaguesAdapter

        // Get sport argument
        val sport = arguments?.getString("sport") ?: ""

        // Initialize the ViewModel
        val leaguesRepository = LeaguesRepository()
        val viewModelFactory = LeaguesViewModelFactory(leaguesRepository)
        viewModel = ViewModelProvider(this, viewModelFactory)[LeaguesViewModel::class.java]

        // Fetch leagues
        viewModel.fetchLeagues(sport)

        // Observe the LiveData
        viewModel.leagues.observe(viewLifecycleOwner) { leagues ->
            leaguesAdapter.submitList(leagues)
        }

        return binding.root
    }

    companion object {
        fun newInstance(sport: String) = LeaguesFragment().apply {
            arguments = Bundle().apply {
                putString("sport", sport)
            }
        }
    }
}
