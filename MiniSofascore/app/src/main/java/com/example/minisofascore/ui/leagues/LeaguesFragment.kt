package com.example.minisofascore.ui.leagues

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.minisofascore.adapters.LeaguesRecyclerAdapter
import com.example.minisofascore.data.enums.SportEnum
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

        // Initialize the ViewModel
        val leaguesRepository = LeaguesRepository()
        val viewModelFactory = LeaguesViewModelFactory(leaguesRepository)
        viewModel = ViewModelProvider(this, viewModelFactory)[LeaguesViewModel::class.java]

        // Get sport argument
        val sport = arguments?.getString("sport") ?: "football"

        // Initialize the adapter
        leaguesAdapter = LeaguesRecyclerAdapter()

        // Set up RecyclerView
        binding.leaguesRecyclerView.adapter = leaguesAdapter

        // Fetch leagues
        viewModel.fetchLeagues(sport)

        // Observe the LiveData
        viewModel.leagues.observe(viewLifecycleOwner) { leagues ->
            // Show no leagues text view if there are no leagues
            if (leagues.isEmpty()) {
                binding.noEventsText.visibility = View.VISIBLE
            } else {
                binding.noEventsText.visibility = View.GONE
                leaguesAdapter.submitList(leagues)
            }
        }

        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }

        return binding.root
    }

    companion object {
        private const val ARG_SPORT = "sport"

        @RequiresApi(Build.VERSION_CODES.O)
        fun newInstance(sport: SportEnum): LeaguesFragment {
            val fragment = LeaguesFragment()
            val args = Bundle()
            args.putString(ARG_SPORT, sport.ordinal.toString())
            fragment.arguments = args
            return fragment
        }
    }
}
