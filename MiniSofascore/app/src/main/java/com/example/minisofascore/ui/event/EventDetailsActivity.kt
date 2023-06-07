package com.example.minisofascore.ui.event

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.minisofascore.adapters.IncidentAdapter
import com.example.minisofascore.data.models.Incident
import com.example.minisofascore.databinding.ActivityEventDetailsBinding
import com.example.minisofascore.ui.events.EventCache

class EventDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEventDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val selectedEvent = EventCache.selectedEvent

        // Set up the toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Set event details
        binding.sportTextView.text = "Sport"
        binding.tournamentTextView.text = "Tournament"
        binding.roundTextView.text = "Round"

        // Set team names, logos, and scores
        binding.homeTeamName.text = "Home Team"
        binding.homeTeamScore.text = "0"
        binding.awayTeamScore.text = "0"
        binding.awayTeamName.text = "Away Team"

        // Set match status
        binding.matchStatusTextView.text = "Match Status"

        // Set up the incidents RecyclerView
        val incidents = listOf(
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
            Incident(
                player = "ppp",
                scoringTeam = "home",
                homeScore = 1,
                awayScore = 1,
                goalType = "regular",
                id = 2889,
                time = 15,
                type = "goal"
            ),
            Incident(
                player = "Player 3",
                scoringTeam = "home",
                homeScore = 2,
                awayScore = 1,
                goalType = "regular",
                id = 2890,
                time = 20,
                type = "goal"
            )
        )
        val incidentAdapter = IncidentAdapter(incidents)
        binding.incidentsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.incidentsRecyclerView.adapter = incidentAdapter
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
