package com.example.minisofascore.ui.team.standings

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.minisofascore.R

class TeamStandingsFragment : Fragment() {

    companion object {
        fun newInstance(teamId: String) = TeamStandingsFragment()
    }

    private lateinit var viewModel: TeamStandingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_team_standings, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TeamStandingsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}