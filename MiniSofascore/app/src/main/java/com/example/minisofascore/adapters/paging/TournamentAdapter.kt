package com.example.minisofascore.adapters.paging

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.minisofascore.R
import com.example.minisofascore.data.models.Tournament
import com.example.minisofascore.databinding.ItemTournamentBinding

class TournamentAdapter(
    private val context: Context
) : PagingDataAdapter<Tournament, TournamentAdapter.TournamentViewHolder>(TournamentComparator) {

    inner class TournamentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemTournamentBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TournamentViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_tournament, parent, false)
        return TournamentViewHolder(view)
    }

    override fun onBindViewHolder(holder: TournamentViewHolder, position: Int) {
        val tournament = getItem(position)
        holder.binding.tournamentName.text = tournament?.name
        holder.binding.countryName.text = tournament?.country?.name

        val logoUrl = "https://academy.dev.sofascore.com/tournament/${tournament?.id}/image"
        Glide.with(holder.itemView)
            .load(logoUrl)
            .into(holder.binding.tournamentLogo)

        // TODO: Setup RecyclerView for events
        holder.binding.eventsRecyclerView.layoutManager = LinearLayoutManager(context)
        //holder.binding.eventsRecyclerView.adapter = EventAdapter(context, tournament?.events) // Here we're setting EventAdapter which is not yet defined
    }

    object TournamentComparator : DiffUtil.ItemCallback<Tournament>() {
        override fun areItemsTheSame(oldItem: Tournament, newItem: Tournament): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Tournament, newItem: Tournament): Boolean {
            return oldItem == newItem
        }
    }
}
