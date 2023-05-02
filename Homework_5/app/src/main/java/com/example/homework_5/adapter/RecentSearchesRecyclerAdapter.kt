package com.example.homework_5.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homework_5.R
import com.example.homework_5.databinding.CityItemViewBinding
import com.example.homework_5.helpers.fromLatLonToDMS
import com.example.homework_5.model.CurrentLocationWeather

class RecentSearchesRecyclerAdapter(
    private val context: Context,
    private var recentSearchesList: MutableList<CurrentLocationWeather>,
    private val onItemClick: (CurrentLocationWeather) -> Unit
) : RecyclerView.Adapter<RecentSearchesRecyclerAdapter.RecentSearchViewHolder>() {

    class RecentSearchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = CityItemViewBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentSearchViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.city_item_view, parent, false)
        return RecentSearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecentSearchViewHolder, position: Int) {
        val recentSearch = recentSearchesList[position]

        holder.binding.apply {
            // Set the data you want to display for each recent search item
            // For example, set the location name in a TextView
            title.text = recentSearch.location.name
            label1.text = fromLatLonToDMS(recentSearch.location.lat, recentSearch.location.lon)
            label2.text = R.string.distance.toString() + ": 8542" + "km"
            temperature.text = recentSearch.current.temp_c.toString() + "°"

            // Load the weather icon from the URL using Glide
            Glide.with(context)
                .load(recentSearch.current.condition.icon)
                .into(weatherIcon)
        }

        // Add click listener to item view if necessary
        holder.itemView.setOnClickListener {
            onItemClick(recentSearch)
        }
    }

    override fun getItemCount(): Int {
        return recentSearchesList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clear() {
        recentSearchesList.clear()
        notifyDataSetChanged()
    }
}
