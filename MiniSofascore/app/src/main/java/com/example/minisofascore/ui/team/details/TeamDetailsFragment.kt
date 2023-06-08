package com.example.minisofascore.ui.team.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.minisofascore.R
import com.example.minisofascore.data.repositories.CountryRepository
import com.example.minisofascore.data.repositories.TeamRepository
import com.example.minisofascore.databinding.FragmentTeamDetailsBinding
import com.example.minisofascore.ui.team.TeamCache
import com.example.minisofascore.ui.team.TeamViewModelFactory

class TeamDetailsFragment : Fragment() {
    private lateinit var binding: FragmentTeamDetailsBinding
    private lateinit var viewModel: TeamDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTeamDetailsBinding.inflate(inflater, container, false)

        // Initialize the ViewModel
        val teamRepository = TeamRepository()
        val countryRepository = CountryRepository()
        val viewModelFactory = TeamViewModelFactory(teamRepository, countryRepository)
        viewModel = ViewModelProvider(this, viewModelFactory)[TeamDetailsViewModel::class.java]

        val teamId = TeamCache.selectedTeam?.id
        if (teamId != null) {
            viewModel.fetchTeamDetails(teamId)
            viewModel.fetchTeamPlayers(teamId)
            viewModel.fetchTeamTournaments(teamId)
            viewModel.fetchNextMatch(teamId)
        }

        viewModel.teamDetails.observe(viewLifecycleOwner) { team ->
            val coach = getString(R.string.coach) + ": " + team?.managerName
            val country = team?.country?.name
            val venue = team?.venue

            if (country != null) {
                viewModel.fetchCountryFlag(country)
            }

            binding.coachNameTextView.text = coach
            binding.coachCountryNameTextView.text = country
            binding.stadiumTextView.text = venue
        }

        viewModel.countryFlag.observe(viewLifecycleOwner) { countryFlag ->
            // Display the country flag
            // TODO: You need to update this to use the actual flag information
            //binding.coachCountryImageView.setImageBitmap(countryFlag.flags.svg)
        }

        viewModel.teamPlayers.observe(viewLifecycleOwner) { players ->
            val playersCount = players?.size

            binding.teamPlayersCountTextView.text = playersCount?.toString()
            // You can calculate foreignPlayersCount here if needed
            // binding.foreignPlayersCountTextView.text = foreignPlayersCount?.toString()
        }

        viewModel.teamTournaments.observe(viewLifecycleOwner) { tournaments ->
            // Do something with tournaments if needed
        }

        viewModel.teamEvents.observe(viewLifecycleOwner) { events ->
            val nextMatch = events?.first()

            binding.countryName.text = nextMatch?.tournament?.country?.name
            binding.tournamentName.text = nextMatch?.tournament?.name
            binding.startTime.text = nextMatch?.startDate
            binding.overTime.text = nextMatch?.status
            binding.titleTeam1.text = nextMatch?.homeTeam?.name
            binding.titleTeam2.text = nextMatch?.awayTeam?.name
        }

        return binding.root
    }

    companion object {
        private const val ARG_TEAM_ID = "team_id"
        fun newInstance(teamId: String): TeamDetailsFragment {
            val fragment = TeamDetailsFragment()
            val args = Bundle()
            args.putInt(ARG_TEAM_ID, teamId.toInt())
            fragment.arguments = args
            return fragment
        }
    }
}
