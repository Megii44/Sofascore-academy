package com.example.minisofascore.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.minisofascore.R
import com.example.minisofascore.data.models.Incident

class IncidentAdapter(private val incidents: List<Incident>) : RecyclerView.Adapter<IncidentAdapter.IncidentViewHolder>() {

    class IncidentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //val titleTextView: TextView = view.findViewById(R.id.league_name)
        //val teamTextView: TextView = view.findViewById(R.id.awayTeamName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IncidentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_incident, parent, false)
        return IncidentViewHolder(view)
    }

    override fun onBindViewHolder(holder: IncidentViewHolder, position: Int) {
        val incident = incidents[position]
        //holder.titleTextView.text = incident.type
        //holder.teamTextView.text = incident.scoringTeam
    }

    override fun getItemCount(): Int {
        return incidents.size
    }
}
