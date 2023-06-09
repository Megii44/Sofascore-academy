package com.example.minisofascore.adapters.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.minisofascore.R
import com.example.minisofascore.data.models.Player

class PlayersRecyclerAdapter : RecyclerView.Adapter<PlayersRecyclerAdapter.PlayerViewHolder>() {

    private val players: MutableList<Player> = mutableListOf()

    class PlayerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val playerName: TextView = view.findViewById(R.id.player_name_text_view)
        val playerImage: ImageView = view.findViewById(R.id.player_image_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_player, parent, false)
        return PlayerViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player = players[position]
        holder.playerName.text = player.name

        val playerImageUrl = "https://academy.dev.sofascore.com/player/" + player.id + "/image"

        Glide.with(holder.itemView)
            .load(playerImageUrl)
            .into(holder.playerImage)
    }

    override fun getItemCount(): Int {
        return players.size
    }

    fun submitList(newList: List<Player>) {
        players.clear()
        players.addAll(newList)
        notifyDataSetChanged()
    }

    // Override getItemId to provide stable ids for the items
    override fun getItemId(position: Int): Long {
        return players[position].id.toLong()
    }

    // Override getItemViewType to provide a unique view type for the items
    override fun getItemViewType(position: Int): Int {
        return position
    }
}
