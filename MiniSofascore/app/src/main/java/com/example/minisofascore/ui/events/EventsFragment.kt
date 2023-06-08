package com.example.minisofascore.ui.events

import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.example.minisofascore.R
import com.example.minisofascore.adapters.recycler.EventsRecyclerAdapter
import com.example.minisofascore.data.enums.SportEnum
import com.example.minisofascore.databinding.FragmentEventsBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class EventsFragment : Fragment() {
    private lateinit var binding: FragmentEventsBinding
    lateinit var viewModel: EventsViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentEventsBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this)[EventsViewModel::class.java]

        val sport = arguments?.getInt(ARG_SPORT)?.let { SportEnum.values()[it] }
        val date = getArgumentsDate()

        if (date != null) {
            viewModel.selectDay(date)
        } else {
            viewModel.selectDay(LocalDate.now())
        }

        if (sport != null) {
            viewModel.selectSport(sport.ordinal)
        } else {
            viewModel.selectSport(SportEnum.Football.ordinal)
        }

        viewModel.getEvents(null, null)

        viewModel.eventsGroupedByTournament.observe(viewLifecycleOwner) { eventsGrouped ->
            if (eventsGrouped.isEmpty()) {
                binding.noEventsText.visibility = View.VISIBLE
            } else {
                binding.noEventsText.visibility = View.GONE
                binding.footballEventsRecyclerView.adapter = EventsRecyclerAdapter(requireContext(),
                    eventsGrouped
                )
            }
            // Update the number of events text
            binding.eventsCountText.text = resources.getString(R.string.events_count_text, eventsGrouped.sumOf { it.second.size })
        }

        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }

        viewModel.selectedSport.observe(viewLifecycleOwner) { position ->
            val selectedSport = SportEnum.values().getOrElse(position) { SportEnum.Football }
            viewModel.getEvents(selectedSport.toString(), viewModel.selectedDay.value?.toString() ?: LocalDate.now().toString())
        }

        viewModel.selectedDay.observe(viewLifecycleOwner) { selectedDate ->
            val selectedSport = SportEnum.values().getOrElse(viewModel.selectedSport.value ?: 0) { SportEnum.Football }
            viewModel.getEvents(selectedSport.toString(), selectedDate.toString())
            // Update the selected day text
            val dateFormatter = DateTimeFormatter.ofPattern("EEE, dd.MM.yyyy.")
            binding.selectedDayText.text = selectedDate.format(dateFormatter)
        }

        return binding.root
    }

    companion object {
        private const val ARG_DATE = "date"
        private const val ARG_SPORT = "sport"

        @RequiresApi(Build.VERSION_CODES.O)
        fun newInstance(date: LocalDate, sport: SportEnum): EventsFragment {
            val fragment = EventsFragment()
            val args = Bundle()
            args.putString(ARG_DATE, date.toString())
            args.putInt(ARG_SPORT, sport.ordinal)
            fragment.arguments = args
            return fragment
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun getArgumentsDate(): LocalDate? {
        val dateString = arguments?.getString(ARG_DATE)
        return dateString?.let {
            LocalDate.parse(it, DateTimeFormatter.ISO_DATE)
        }
    }
}
