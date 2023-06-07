package com.example.minisofascore.ui.team.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.minisofascore.R

class TeamDetailsFragment : Fragment() {

    companion object {
        fun newInstance(teamId: String) = TeamDetailsFragment()
    }

    private lateinit var viewModel: TeamDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_team_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val teamId = arguments?.getString("team_id") ?: ""
    }


}