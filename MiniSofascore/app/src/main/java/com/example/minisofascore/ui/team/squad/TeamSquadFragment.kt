package com.example.minisofascore.ui.team.squad

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.minisofascore.adapters.recycler.PlayersRecyclerAdapter
import com.example.minisofascore.data.enums.SportEnum
import com.example.minisofascore.data.repositories.CountryRepository
import com.example.minisofascore.data.repositories.TeamRepository
import com.example.minisofascore.databinding.FragmentTeamSquadBinding
import com.example.minisofascore.ui.team.TeamCache
import com.example.minisofascore.ui.team.TeamViewModel
import com.example.minisofascore.ui.team.TeamViewModelFactory
import com.example.minisofascore.ui.team.details.TeamDetailsFragment

class TeamSquadFragment : Fragment() {
    private lateinit var binding: FragmentTeamSquadBinding
    private lateinit var viewModel: TeamViewModel
    private lateinit var playersAdapter: PlayersRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTeamSquadBinding.inflate(inflater, container, false)

        // Initialize the ViewModel
        val playersRepository = TeamRepository()
        val countryRepository = CountryRepository()
        val viewModelFactory = TeamViewModelFactory(playersRepository, countryRepository)
        viewModel = ViewModelProvider(this, viewModelFactory)[TeamViewModel::class.java]

        // Get team argument
        val teamId = TeamCache.selectedTeam?.id

        // Initialize the adapter
        playersAdapter = PlayersRecyclerAdapter()

        // Set up RecyclerView
        // Set up the RecyclerView with LinearLayoutManager
        binding.playersRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = playersAdapter
        }

        // Fetch players
        if (teamId != null) {
            viewModel.fetchTeamPlayers(teamId)
        }

        // Observe the LiveData
        viewModel.teamPlayers.observe(viewLifecycleOwner) { players ->
            // Show no players text view if there are no players
            if (players.isEmpty()) {
                binding.noPlayersInThisTeamText.visibility = View.VISIBLE
            } else {
                binding.noPlayersInThisTeamText.visibility = View.GONE
                playersAdapter.submitList(players)
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
        private const val ARG_TEAM_ID = "team_id"
        fun newInstance(teamId: String): TeamSquadFragment {
            return TeamSquadFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_TEAM_ID, teamId.toInt())
                }
            }
        }
    }
}
