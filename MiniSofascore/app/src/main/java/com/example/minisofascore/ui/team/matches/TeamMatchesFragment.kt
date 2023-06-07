package com.example.minisofascore.ui.team.matches

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.minisofascore.R

class TeamMatchesFragment : Fragment() {

    companion object {
        fun newInstance(teamId: String) = TeamMatchesFragment()
    }

    private lateinit var viewModel: TeamMatchesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_team_matches, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TeamMatchesViewModel::class.java)
        // TODO: Use the ViewModel
    }

}