package com.example.minisofascore.ui.team.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.minisofascore.R
import com.example.minisofascore.adapters.recycler.TournamentRecyclerAdapter
import com.example.minisofascore.data.repositories.CountryRepository
import com.example.minisofascore.data.repositories.TeamRepository
import com.example.minisofascore.databinding.FragmentTeamDetailsBinding
import com.example.minisofascore.ui.team.TeamCache
import com.example.minisofascore.ui.team.TeamViewModel
import com.example.minisofascore.ui.team.TeamViewModelFactory
import com.example.minisofascore.ui.utils.loadImage
import kotlinx.coroutines.launch

class TeamDetailsFragment : Fragment() {
    private lateinit var binding: FragmentTeamDetailsBinding
    private lateinit var viewModel: TeamViewModel
    private lateinit var tournamentAdapter: TournamentRecyclerAdapter
    private val teamRepository by lazy { TeamRepository() }
    private val countryRepository by lazy { CountryRepository() }
    private val viewModelFactory by lazy { TeamViewModelFactory(teamRepository, countryRepository) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTeamDetailsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this, viewModelFactory)[TeamViewModel::class.java]

        // Initialize the adapter
        tournamentAdapter = TournamentRecyclerAdapter(emptyList())

        val layoutManager = GridLayoutManager(context, 3) // '3' is the number of columns
        binding.tournamentsRecyclerView.adapter = tournamentAdapter
        binding.tournamentsRecyclerView.layoutManager = layoutManager

        val teamId = TeamCache.selectedTeam?.id
        teamId?.let { id ->
            lifecycleScope.launch {
                viewModel.apply {
                    fetchTeamDetails(id)
                    fetchTeamPlayers(id)
                    fetchTeamTournaments(id)
                    fetchNextMatch(id)

                    val countryName = TeamCache.selectedTeam?.country?.name
                    if (countryName != null) {
                        fetchCountryFlag(countryName)
                    }
                }
            }
        }

        setupObservers()

        return binding.root
    }

    private fun setupObservers() {
        viewModel.teamDetails.observe(viewLifecycleOwner) { team ->
            val coach = getString(R.string.coach) + ": " + team?.managerName
            val country = team?.country?.name
            val venue = team?.venue

            with(binding) {
                coachNameTextView.text = coach
                coachCountryNameTextView.text = country
                stadiumTextView.text = venue
                loadImage(requireContext(), "https://academy.dev.sofascore.com/player/1/image", coachImageView)
            }
        }

        viewModel.teamPlayers.observe(viewLifecycleOwner) { players ->
            binding.teamPlayersCountTextView.text = players?.size?.toString()
            // Calculate foreignPlayersCount here if needed
            // binding.foreignPlayersCountTextView.text = foreignPlayersCount?.toString()
        }

        viewModel.countryFlag.observe(viewLifecycleOwner) {country ->
            with(binding) {
                val flagUrl = country.flags.png
                loadImage(requireContext(), flagUrl, coachCountryImageView)
            }
        }

        viewModel.teamEvents.observe(viewLifecycleOwner) { events ->
            events?.first()?.let { event ->
                with(binding) {
                    countryName.text = event.tournament.country.name
                    tournamentName.text = event.tournament.name
                    startTime.text = event.startDate
                    overTime.text = event.status
                    titleTeam1.text = event.homeTeam.name
                    titleTeam2.text = event.awayTeam.name

                    loadImage(requireContext(), "https://academy.dev.sofascore.com/tournament/${event.tournament.id}/image", tournamentLogo)
                    loadImage(requireContext(), "https://academy.dev.sofascore.com/team/${event.homeTeam.id}/image", logoTeam1)
                    loadImage(requireContext(), "https://academy.dev.sofascore.com/team/${event.awayTeam.id}/image", logoTeam2)
                }
            }
        }

        viewModel.teamTournaments.observe(viewLifecycleOwner) { tournaments ->
            tournamentAdapter.updateTournaments(tournaments)
        }
    }

    companion object {
        private const val ARG_TEAM_ID = "team_id"
        fun newInstance(teamId: String): TeamDetailsFragment {
            return TeamDetailsFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_TEAM_ID, teamId.toInt())
                }
            }
        }
    }
}
