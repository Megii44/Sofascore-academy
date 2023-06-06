package com.example.minisofascore.ui.events

import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.example.minisofascore.adapters.EventsRecyclerAdapter
import com.example.minisofascore.data.models.EventResponse
import com.example.minisofascore.databinding.FragmentEventsBinding
import java.time.LocalDate

class EventsFragment : Fragment() {
    private lateinit var binding: FragmentEventsBinding
    private lateinit var viewModel: EventsViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentEventsBinding.inflate(inflater, container, false)

        val sport = arguments?.getString("sportType")
        val dateString = arguments?.getString("day")

        viewModel = ViewModelProvider(this)[EventsViewModel::class.java]

        viewModel.getEvents(sport ?: "football", dateString ?: LocalDate.now().toString())

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
        private const val ARG_SPORT = "sport"

        @RequiresApi(Build.VERSION_CODES.O)
        fun newInstance(date: LocalDate, sport: String): EventsFragment {
            val fragment = EventsFragment()
            val args = Bundle()
            args.putString(ARG_DATE, date.toString())
            args.putString(ARG_SPORT, sport)
            fragment.arguments = args
            return fragment
        }
    }
}