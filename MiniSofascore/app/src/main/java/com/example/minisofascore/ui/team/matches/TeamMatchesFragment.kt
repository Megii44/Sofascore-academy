package com.example.minisofascore.ui.team.matches

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.minisofascore.adapters.paging.EventAdapter
import com.example.minisofascore.data.factories.MatchViewModelFactory
import com.example.minisofascore.data.repositories.MatchRepository
import com.example.minisofascore.database.MiniSofascoreDatabase
import com.example.minisofascore.data.source.EventLocalDataSource
import com.example.minisofascore.data.source.EventRemoteDataSource
import com.example.minisofascore.databinding.FragmentTeamMatchesBinding
import com.example.minisofascore.network.Network
import com.example.minisofascore.ui.team.TeamViewModel

class TeamMatchesFragment : Fragment() {

    private lateinit var binding: FragmentTeamMatchesBinding
    private lateinit var viewModel: TeamViewModel
    private val eventAdapter: EventAdapter by lazy {
        EventAdapter(requireContext())
    }
    private var teamId: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTeamMatchesBinding.inflate(inflater, container, false)

        // Initialize the ViewModel
        val network = Network()

        /*val localDataSource = MiniSofascoreDatabase.getDatabase(requireContext())
            ?.let { EventLocalDataSource(it.EventDao()) }
        val remoteDataSource = EventRemoteDataSource(network.getService())
        val eventRepository = localDataSource?.let { MatchRepository(remoteDataSource, it) }
        val viewModelFactory = eventRepository?.let { MatchViewModelFactory(it) }
        if (viewModelFactory != null) {
            viewModel = viewModelFactory.create(TeamViewModel::class.java)
        }*/

        // Get the team ID from arguments
        teamId = arguments?.getInt(ARG_TEAM_ID) ?: throw IllegalArgumentException("Team ID is required")

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.teamMatchesRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
            adapter = eventAdapter
        }
    }

    companion object {
        private const val ARG_TEAM_ID = "team_id"

        fun newInstance(teamId: String): TeamMatchesFragment {
            return TeamMatchesFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_TEAM_ID, teamId.toInt())
                }
            }
        }
    }
}
