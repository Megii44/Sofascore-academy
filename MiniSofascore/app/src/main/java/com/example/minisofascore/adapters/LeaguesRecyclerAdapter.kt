package com.example.minisofascore.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.minisofascore.R
import com.example.minisofascore.data.models.Tournament

class LeaguesRecyclerAdapter : RecyclerView.Adapter<LeaguesRecyclerAdapter.LeagueViewHolder>() {

    private val leagues: MutableList<Tournament> = mutableListOf()

    class LeagueViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val leagueName: TextView = view.findViewById(R.id.league_name)
        val leagueLogo: ImageView = view.findViewById(R.id.league_logo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.league_item, parent, false)
        return LeagueViewHolder(view)
    }

    override fun onBindViewHolder(holder: LeagueViewHolder, position: Int) {
        val league = leagues[position]
        holder.leagueName.text = league.name

        val logoUrl = "https://academy.dev.sofascore.com/tournament/" + league.id + "/image"

        Glide.with(holder.itemView)
            .load(logoUrl)
            .into(holder.leagueLogo)
    }

    override fun getItemCount(): Int {
        return leagues.size
    }

    fun submitList(newList: List<Tournament>) {
        leagues.clear()
        leagues.addAll(newList)
        notifyDataSetChanged()
    }
}
