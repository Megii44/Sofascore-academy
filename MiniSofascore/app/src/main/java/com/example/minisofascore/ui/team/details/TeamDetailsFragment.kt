package com.example.minisofascore.ui.team.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
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
        val viewModelFactory = TeamViewModelFactory(teamRepository)
        viewModel = ViewModelProvider(this, viewModelFactory)[TeamDetailsViewModel::class.java]

        val team = TeamCache.selectedTeam
        val teamId = team?.id

        val coach = team?.managerName
        val country = team?.country?.name
        val venue = team?.venue

        if (teamId != null) {
            viewModel.fetchTeamPlayers(teamId)
            viewModel.fetchTeamTournaments(teamId)
            viewModel.fetchNextMatch(teamId)
        }

        val playersCount = viewModel.teamPlayers.value?.size
        val foreignPlayersCount = null

        val tournaments = viewModel.teamTournaments
        val nextMatch = viewModel.teamEvents.value?.first()

        // Display team details
        binding.coachNameTextView.text = coach
        binding.coachCountryNameTextView.text = country
        binding.teamPlayersCountTextView.text = playersCount.toString()
        binding.foreignPlayersCountTextView.text = playersCount.toString()
        binding.stadiumTextView.text = venue

        binding.countryName.text = nextMatch?.tournament?.country?.name
        binding.tournamentName.text = nextMatch?.tournament?.name
        binding.startTime.text = nextMatch?.startDate
        binding.overTime.text = nextMatch?.status
        binding.titleTeam1.text = nextMatch?.homeTeam?.name
        binding.titleTeam2.text = nextMatch?.awayTeam?.name

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