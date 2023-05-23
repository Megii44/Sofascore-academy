package com.example.minisofascore.adapters

import android.annotation.SuppressLint
import android.content.Context
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
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

class EventsRecyclerAdapter(
    private val context: Context,
    private var events: MutableList<EventResponse>,
) : RecyclerView.Adapter<EventsRecyclerAdapter.EventsViewHolder>() {

    class EventsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = SampleTimeBoxBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.sample_time_box, parent, false)
        return EventsViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        val event = events[position]

        holder.binding.apply {
            // Set the data you want to display for each recent search item
            // For example, set the location name in a TextView
            titleTeam1.text = event.homeTeam.name
            titleTeam2.text = event.awayTeam.name
            scoreTeam1.text = event.homeScore.total.toString()
            scoreTeam2.text = event.awayScore.total.toString()

            val homeTeamLogo = event.homeTeam.logo
            Glide.with(logoTeam1.context)
                .load(homeTeamLogo)
                .into(logoTeam1)

            val awayTeamLogo = event.awayTeam.logo
            Glide.with(logoTeam2.context)
                .load(awayTeamLogo)
                .into(logoTeam2)

            startTime.text = OffsetDateTime.parse(event.startDate).format(DateTimeFormatter.ofPattern("HH:mm"))
            overTime.text = event.status
        }
    }

    override fun getItemCount(): Int {
        return events.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clear() {
        events.clear()
        notifyDataSetChanged()
    }
}
