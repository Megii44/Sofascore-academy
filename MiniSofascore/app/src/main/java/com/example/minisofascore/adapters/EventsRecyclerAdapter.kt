package com.example.minisofascore.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.minisofascore.R
import com.example.minisofascore.data.models.EventResponse
import com.example.minisofascore.databinding.ItemTournamentBinding

class EventsRecyclerAdapter(
    private val context: Context,
    private var eventsGroupedByTournament: List<Pair<String, List<EventResponse>>>
) : RecyclerView.Adapter<EventsRecyclerAdapter.EventsViewHolder>() {

    inner class EventsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemTournamentBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_tournament, parent, false)
        return EventsViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        val (tournament, events) = eventsGroupedByTournament[position]
        holder.binding.tournamentName.text = tournament // set your tournament TextView

        // check if event, tournament and country objects are not null before accessing name and id
        val event = events.getOrNull(position)
        val countryName = event?.tournament?.country?.name
        val tournamentId = event?.tournament?.id
        if (countryName != null) {
            holder.binding.countryName.text = countryName
        }

        if (tournamentId != null) {
            val logoUrl = "https://academy.dev.sofascore.com/tournament/$tournamentId/image"
            Glide.with(holder.itemView)
                .load(logoUrl)
                .into(holder.binding.tournamentLogo)
        }

        // Set the RecyclerView adapter for the list of events
        val eventAdapter = EventAdapter(context, events)
        holder.binding.eventsRecyclerView.layoutManager = LinearLayoutManager(context)
        holder.binding.eventsRecyclerView.adapter = eventAdapter
    }


    override fun getItemCount(): Int {
        return eventsGroupedByTournament.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newData: List<Pair<String, List<EventResponse>>>) {
        this.eventsGroupedByTournament = newData
        notifyDataSetChanged()
    }
}
