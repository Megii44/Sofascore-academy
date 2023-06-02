package com.example.minisofascore.ui.football

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.minisofascore.adapters.EventsRecyclerAdapter
import com.example.minisofascore.data.models.EventResponse
import com.example.minisofascore.databinding.FragmentFootballBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class FootballFragment : Fragment() {

    private lateinit var binding: FragmentFootballBinding
    private lateinit var viewModel: FootballViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentFootballBinding.inflate(inflater, container, false)

        val date = arguments?.getSerializable(ARG_DATE) as LocalDate

        viewModel = ViewModelProvider(this)[FootballViewModel::class.java]

        viewModel.events.observe(viewLifecycleOwner) { events ->
            binding.footballEventsRecyclerView.adapter = EventsRecyclerAdapter(requireContext(),
                events as MutableList<EventResponse>
            )
        }

        // Format the LocalDate to a string (using your desired date format)
        val dateString = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))

        // Call getEvents with your query
        viewModel.getEvents("football", dateString)

        return binding.root
    }

    companion object {
        private const val ARG_DATE = "date"

        @RequiresApi(Build.VERSION_CODES.O)
        fun newInstance(date: LocalDate): FootballFragment {
            val fragment = FootballFragment()
            val args = Bundle()
            args.putSerializable(ARG_DATE, date)
            fragment.arguments = args
            return fragment
        }
    }

}
