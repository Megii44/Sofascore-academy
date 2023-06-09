package com.example.minisofascore.ui.team.matches

import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.minisofascore.adapters.paging.TournamentAdapter
import com.example.minisofascore.databinding.FragmentEventsBinding
import kotlinx.coroutines.launch

class TeamMatchesFragment : Fragment() {
    private lateinit var binding: FragmentEventsBinding
    private lateinit var viewModel: TeamMatchesViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentEventsBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this)[TeamMatchesViewModel::class.java]

        val adapter = TournamentAdapter(requireContext())
        binding.footballEventsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.footballEventsRecyclerView.adapter = adapter

        lifecycleScope.launch {
            //viewModel.teamEventsFlow.collectLatest {
              //  adapter.submitData(it)
            //}
        }

        return binding.root
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
