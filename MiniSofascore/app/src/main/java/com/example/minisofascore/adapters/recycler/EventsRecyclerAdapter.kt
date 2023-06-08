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
import com.example.minisofascore.databinding.ItemEventBinding
import com.example.minisofascore.ui.event.EventDetailsActivity
import com.example.minisofascore.ui.events.EventCache
import com.example.minisofascore.ui.utils.getEventStatusAbr
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class EventsRecyclerAdapter(
    private val context: Context,
    private var events: List<EventResponse>
) : RecyclerView.Adapter<EventsRecyclerAdapter.EventViewHolder>() {

    class EventViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemEventBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_event, parent, false)
        return EventViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = events[position]

        holder.binding.apply {
            // Set the data you want to display for each event
            homeTeamName.text = event.homeTeam.name
            awayTeamName.text = event.awayTeam.name
            homeTeamScore.text = event.homeScore.total.toString()
            awayTeamScore.text = event.awayScore.total.toString()

            val homeTeamLogoUrl = "https://academy.dev.sofascore.com/team/" + event.homeTeam.id.toString() + "/image"
            Glide.with(homeTeamLogo.context)
                .load(homeTeamLogoUrl)
                .into(homeTeamLogo)

            val awayTeamLogoUrl = "https://academy.dev.sofascore.com/team/" + event.awayTeam.id.toString() + "/image"
            Glide.with(awayTeamLogo.context)
                .load(awayTeamLogoUrl)
                .into(awayTeamLogo)

            val offsetDateTime = OffsetDateTime.parse(event.startDate)
            val zonedDateTime = offsetDateTime.atZoneSameInstant(ZoneId.of("CET"))
            val formattedTime = zonedDateTime.format(DateTimeFormatter.ofPattern("HH:mm"))
            startTime.text = formattedTime

            status.text = getEventStatusAbr(context, event.status)

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
