package com.example.minisofascore.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.minisofascore.R
import com.example.minisofascore.data.models.Event

class EventAdapter(private val events: List<Event>) : RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // TODO: Bind views
        val startTime: TextView = itemView.findViewById(R.id.start_time)
        val overTime: TextView = itemView.findViewById(R.id.over_time)
        // Add other views...
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_football, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = events[position]
        holder.startTime.text = event.startTime
        holder.overTime.text = event.overTime
        // Bind other views...
    }

    override fun getItemCount() = events.size
}
