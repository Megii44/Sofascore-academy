package com.example.minisofascore.ui.event

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.minisofascore.R
import com.example.minisofascore.adapters.IncidentAdapter
import com.example.minisofascore.data.models.Incident
import com.example.minisofascore.databinding.ActivityEventDetailsBinding
import com.example.minisofascore.ui.events.EventCache
import com.example.minisofascore.ui.utils.checkEventStatus
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class EventDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEventDetailsBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
                    binding.matchStatusTextView.text = "Match Not Started"
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
                    binding.matchStatusTextView.text = "Match In Play"
                }
                else -> {
                    // Event has already finished
                    binding.homeTeamScore.text = it.homeScore.total.toString()
                    binding.awayTeamScore.text = it.awayScore.total.toString()
                    binding.dateText.visibility = View.INVISIBLE
                    binding.timeText.visibility = View.INVISIBLE
                    binding.matchStatusTextView.text = "Match Finished"
                }
            }
        }

        // Set up the incidents RecyclerView
        // Consider fetching incidents from a data source instead of hardcoding
        val incidents = getIncidents()
        val incidentAdapter = IncidentAdapter(incidents)
        binding.incidentsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.incidentsRecyclerView.adapter = incidentAdapter
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun getIncidents(): List<Incident> {
        // Replace this with your actual data source
        return listOf(
            Incident(
                player = "Julián Álvarez",
                scoringTeam = "away",
                homeScore = 0,
                awayScore = 1,
                goalType = "regular",
                id = 2888,
                time = 8,
                type = "goal"
            ),
            // more incidents...
        )
    }
}
