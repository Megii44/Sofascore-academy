package com.example.minisofascore.ui.team.squad

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.minisofascore.R

class TeamSquadFragment : Fragment() {

    companion object {
        fun newInstance(teamId: String) = TeamSquadFragment()
    }

    private lateinit var viewModel: TeamSquadViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_team_squad, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TeamSquadViewModel::class.java)
        // TODO: Use the ViewModel
    }

}