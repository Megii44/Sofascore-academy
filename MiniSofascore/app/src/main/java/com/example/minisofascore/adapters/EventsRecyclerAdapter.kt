package com.example.minisofascore.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.minisofascore.R
import com.example.minisofascore.data.models.EventResponse
import com.example.minisofascore.databinding.SampleTimeBoxBinding

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

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        val event = events[position]

        holder.binding.apply {
            // Set the data you want to display for each recent search item
            // For example, set the location name in a TextView
            titleTeam1.text = event.homeTeam.name
            titleTeam2.text = event.awayTeam.name
            scoreTeam1.text = event.homeScore.toString()
            scoreTeam2.text = event.awayScore.toString()
            //val homeTeamLogo = getTeamLogo(event.homeTeam.id)
            //val awayTeamLogo = getTeamLogo(event.homeTeam.id)
            //logoTeam1.setImageResource(homeTeamLogo)
            //logoTeam2.setImageResource(awayTeamLogo)
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
