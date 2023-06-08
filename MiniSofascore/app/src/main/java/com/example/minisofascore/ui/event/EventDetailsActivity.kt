package com.example.minisofascore.ui.event

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.minisofascore.R
import com.example.minisofascore.adapters.recycler.IncidentRecyclerAdapter
import com.example.minisofascore.data.repositories.EventDetailsRepository
import com.example.minisofascore.databinding.ActivityEventDetailsBinding
import com.example.minisofascore.ui.events.EventCache
import com.example.minisofascore.ui.team.TeamCache
import com.example.minisofascore.ui.team.TeamDetailsActivity
import com.example.minisofascore.ui.utils.checkEventStatus
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class EventDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEventDetailsBinding
    private lateinit var viewModel: EventDetailsViewModel
    private lateinit var incidentAdapter: IncidentRecyclerAdapter

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Create the repository instance
        val eventDetailsRepository = EventDetailsRepository()

        // Initialize the ViewModel using the custom factory
        val viewModelFactory = EventDetailsViewModelFactory(eventDetailsRepository)
        viewModel = ViewModelProvider(this, viewModelFactory)[EventDetailsViewModel::class.java]

        val selectedEvent = EventCache.selectedEvent

        // Set up the toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val actionBarLayout = layoutInflater.inflate(R.layout.custom_action_bar, null)
        binding.toolbar.addView(actionBarLayout)

        // Set team names, logos, and scores
        selectedEvent?.let {
            val tournamentId = selectedEvent.tournament.id
            val tournamentLogoUrl = "https://academy.dev.sofascore.com/tournament/${tournamentId}/image"
            val sport = selectedEvent.tournament.sport.name
            val country = selectedEvent.tournament.country.name
            val tournament = selectedEvent.tournament.name
            val round = "Round " + selectedEvent.round

            // Set toolbar title
            val title = "${sport}, ${country}, ${tournament}, $round"
            val actionBarTitle = actionBarLayout.findViewById<View>(R.id.action_bar_title)
            if (actionBarTitle is TextView) {
                actionBarTitle.text = title
            }

            // Set toolbar image
            val actionBarImage = actionBarLayout.findViewById<View>(R.id.action_bar_image)
            if (actionBarImage is ImageView) {
                Glide.with(this)
                    .load(tournamentLogoUrl)
                    .into(actionBarImage)
            }

            binding.homeTeamName.text = it.homeTeam.name
            binding.awayTeamName.text = it.awayTeam.name

            val homeTeamLogoUrl = "https://academy.dev.sofascore.com/team/${it.homeTeam.id}/image"
            val awayTeamLogoUrl = "https://academy.dev.sofascore.com/team/${it.awayTeam.id}/image"

            Glide.with(this)
                .load(homeTeamLogoUrl)
                .into(binding.homeTeamLogo)

            Glide.with(this)
                .load(awayTeamLogoUrl)
                .into(binding.awayTeamLogo)

            binding.homeTeamLogo.setOnClickListener {
                TeamCache.selectedTeam = selectedEvent.homeTeam
                val intent = Intent(this, TeamDetailsActivity::class.java)
                intent.putExtra("team", selectedEvent.homeTeam)
                startActivity(intent)
            }

            binding.awayTeamLogo.setOnClickListener {
                TeamCache.selectedTeam = selectedEvent.awayTeam
                val intent = Intent(this, TeamDetailsActivity::class.java)
                intent.putExtra("team", selectedEvent.awayTeam)
                startActivity(intent)
            }

            val startTime = it.startDate

            when (checkEventStatus(startTime)) {
                0 -> {
                    // Event has not started
                    val dateTime = LocalDateTime.parse(startTime, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
                    val dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
                    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
                    binding.dateText.text = dateTime.format(dateFormatter)
                    binding.timeText.text = dateTime.format(timeFormatter)
                    binding.homeTeamScore.visibility = View.INVISIBLE
                    binding.awayTeamScore.visibility = View.INVISIBLE
                    binding.middleDash.visibility = View.INVISIBLE
                    binding.matchStatusTextView.text = it.status
                }
                1 -> {
                    // Event is currently in play
                    binding.homeTeamScore.setTextColor(Color.RED)
                    binding.awayTeamScore.setTextColor(Color.RED)
                    binding.middleDash.setTextColor(Color.RED)
                    binding.homeTeamScore.text = it.homeScore.total.toString()
                    binding.awayTeamScore.text = it.awayScore.total.toString()
                    binding.dateText.visibility = View.INVISIBLE
                    binding.timeText.visibility = View.INVISIBLE
                    binding.matchStatusTextView.text = it.status
                }
                else -> {
                    // Event has already finished
                    binding.homeTeamScore.text = it.homeScore.total.toString()
                    binding.awayTeamScore.text = it.awayScore.total.toString()
                    binding.dateText.visibility = View.INVISIBLE
                    binding.timeText.visibility = View.INVISIBLE
                    binding.matchStatusTextView.visibility = View.INVISIBLE
                }
            }
        }

        // initialize the incident adapter
        incidentAdapter = IncidentRecyclerAdapter()

        // Set the adapter on your RecyclerView
        val recyclerView = binding.incidentsRecyclerView
        recyclerView.adapter = incidentAdapter

        // Set observer on the incidents LiveData in the ViewModel
        viewModel.incidents.observe(this) { incidents ->
            // Populate the adapter with data
            incidentAdapter.submitList(incidents)
        }

        // Fetch the incidents for the selected event
        selectedEvent?.let {
            viewModel.fetchIncidents(it.id)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
