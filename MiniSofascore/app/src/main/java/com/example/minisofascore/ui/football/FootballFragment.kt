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

        val date = LocalDate.parse(arguments?.getString(ARG_DATE))

        // Display the selected day
        binding.selectedDayText.text = date.format(DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy"))

        viewModel = ViewModelProvider(this)[FootballViewModel::class.java]

        // Format the LocalDate to a string (using your desired date format)
        val dateString = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))

        // Call getEvents with your query
        viewModel.getEvents("football", dateString)

        viewModel.events.observe(viewLifecycleOwner) { events ->
            // Show no events text view if there are no events
            if (events.isEmpty()) {
                binding.noEventsText.visibility = View.VISIBLE
            } else {
                binding.noEventsText.visibility = View.GONE
                binding.footballEventsRecyclerView.adapter = EventsRecyclerAdapter(requireContext(),
                    events as MutableList<EventResponse>
                )
            }
        }

        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }

        return binding.root
    }


    companion object {
        private const val ARG_DATE = "date"

        @RequiresApi(Build.VERSION_CODES.O)
        fun newInstance(date: LocalDate): FootballFragment {
            val fragment = FootballFragment()
            val args = Bundle()
            args.putString(ARG_DATE, date.toString())
            fragment.arguments = args
            return fragment
        }
    }

}
