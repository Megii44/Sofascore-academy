package com.example.minisofascore.ui.football

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.minisofascore.adapters.EventsRecyclerAdapter
import com.example.minisofascore.data.models.EventResponse
import com.example.minisofascore.databinding.FragmentFootballBinding

class FootballFragment : Fragment() {

    private lateinit var binding: FragmentFootballBinding
    private lateinit var viewModel: FootballViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentFootballBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this)[FootballViewModel::class.java]

        viewModel.events.observe(viewLifecycleOwner) { events ->
            binding.footballEventsRecyclerView.adapter = EventsRecyclerAdapter(requireContext(),
                events as MutableList<EventResponse>
            )
        }

        // Call getEvents with your query
        viewModel.getEvents("football","2023-05-23")

        return binding.root
    }

}