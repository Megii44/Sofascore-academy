package com.example.minisofascore.ui.football

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.minisofascore.adapters.EventAdapter
import com.example.minisofascore.databinding.FragmentFootballBinding

class FootballFragment : Fragment() {

    private lateinit var binding: FragmentFootballBinding
    private lateinit var viewModel: FootballViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentFootballBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this)[FootballViewModel::class.java]

        viewModel.getEvents("football","2023-05-22") // Call getEvents with your query

        viewModel.events.observe(viewLifecycleOwner) { events ->
            binding.footballEventsRecyclerView.adapter = EventAdapter(requireContext(), events)
        }

        return binding.root
    }

}