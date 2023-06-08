package com.example.minisofascore.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.minisofascore.R
import com.example.minisofascore.data.models.Tournament
import com.bumptech.glide.Glide

class TournamentRecyclerAdapter(
    private var tournaments: List<Tournament>
) : RecyclerView.Adapter<TournamentRecyclerAdapter.TournamentViewHolder>() {

    fun updateTournaments(newTournaments: List<Tournament>) {
        tournaments = newTournaments
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TournamentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tournament_info_tile, parent, false)
        return TournamentViewHolder(view)
    }

    override fun onBindViewHolder(holder: TournamentViewHolder, position: Int) {
        val tournament = tournaments[position]
        holder.tournamentName.text = tournament.name
        Glide.with(holder.itemView.context)
            .load(tournament.logo)
            .into(holder.tournamentLogo)
    }

    override fun getItemCount() = tournaments.size

    class TournamentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tournamentLogo: ImageView = itemView.findViewById(R.id.tournament_logo)
        val tournamentName: TextView = itemView.findViewById(R.id.tournament_name)
    }
}
