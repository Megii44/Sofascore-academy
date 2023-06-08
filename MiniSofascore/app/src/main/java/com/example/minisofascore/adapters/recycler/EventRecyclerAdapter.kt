package com.example.minisofascore.adapters.recycler

import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.minisofascore.R
import com.example.minisofascore.data.models.EventResponse
import com.example.minisofascore.databinding.SampleTimeBoxBinding
import com.example.minisofascore.ui.event.EventDetailsActivity
import com.example.minisofascore.ui.events.EventCache
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class EventRecyclerAdapter(
    private val context: Context,
    private var events: List<EventResponse>
) : RecyclerView.Adapter<EventRecyclerAdapter.EventViewHolder>() {

    class EventViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = SampleTimeBoxBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.sample_time_box, parent, false)
        return EventViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = events[position]

        holder.binding.apply {
            // Set the data you want to display for each event
            titleTeam1.text = event.homeTeam.name
            titleTeam2.text = event.awayTeam.name
            scoreTeam1.text = event.homeScore.total.toString()
            scoreTeam2.text = event.awayScore.total.toString()

            val homeTeamLogo = "https://academy.dev.sofascore.com/team/" + event.homeTeam.id.toString() + "/image"
            Glide.with(logoTeam1.context)
                .load(homeTeamLogo)
                .into(logoTeam1)

            val awayTeamLogo = "https://academy.dev.sofascore.com/team/" + event.awayTeam.id.toString() + "/image"
            Glide.with(logoTeam2.context)
                .load(awayTeamLogo)
                .into(logoTeam2)

            val offsetDateTime = OffsetDateTime.parse(event.startDate)
            val zonedDateTime = offsetDateTime.atZoneSameInstant(ZoneId.of("CET"))
            val formattedTime = zonedDateTime.format(DateTimeFormatter.ofPattern("HH:mm"))
            startTime.text = formattedTime

            overTime.text = event.status

            // Set click listener for the event item
            holder.itemView.setOnClickListener {
                EventCache.selectedEvent = event
                val intent = Intent(context, EventDetailsActivity::class.java)
                context.startActivity(intent)
            }
        }
    }


    override fun getItemCount(): Int {
        return events.size
    }
}
