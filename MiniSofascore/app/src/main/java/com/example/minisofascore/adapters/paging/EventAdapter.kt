package com.example.minisofascore.adapters.paging

import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.minisofascore.R
import com.example.minisofascore.data.enums.EventStatusEnum
import com.example.minisofascore.data.models.Event
import com.example.minisofascore.databinding.ItemEventBinding
import com.example.minisofascore.ui.event.EventDetailsActivity
import com.example.minisofascore.ui.events.EventCache
import com.example.minisofascore.utils.getEventStatusAbr
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class EventAdapter(
    private val context: Context
) : PagingDataAdapter<Event, EventAdapter.EventViewHolder>(EventComparator) {

    inner class EventViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemEventBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_event, parent, false)
        return EventViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = getItem(position)

        holder.binding.apply {
            event?.let {
                // Set the data you want to display for each event
                homeTeamName.text = it.homeTeam.name
                awayTeamName.text = it.awayTeam.name
                homeTeamScore.text = it.homeScore?.total.toString()
                awayTeamScore.text = it.awayScore?.total.toString()

                val homeTeamLogoUrl = "https://academy.dev.sofascore.com/team/" + it.homeTeam.id.toString() + "/image"
                Glide.with(homeTeamLogo.context)
                    .load(homeTeamLogoUrl)
                    .into(homeTeamLogo)

                val awayTeamLogoUrl = "https://academy.dev.sofascore.com/team/" + it.awayTeam.id.toString() + "/image"
                Glide.with(awayTeamLogo.context)
                    .load(awayTeamLogoUrl)
                    .into(awayTeamLogo)

                val offsetDateTime = OffsetDateTime.parse(it.startDate)
                val zonedDateTime = offsetDateTime.atZoneSameInstant(ZoneId.of("CET"))
                val formattedTime = zonedDateTime.format(DateTimeFormatter.ofPattern("HH:mm"))
                startTime.text = formattedTime

                status.text = getEventStatusAbr(context, it.status)

                if(it.status == EventStatusEnum.InProgress.toString()) {
                    status.setTextColor(ContextCompat.getColor(context, R.color.red))
                    homeTeamScore.setTextColor(ContextCompat.getColor(context, R.color.red))
                    awayTeamScore.setTextColor(ContextCompat.getColor(context, R.color.red))
                }
            }

            // Set click listener for the event item
            holder.itemView.setOnClickListener {
                EventCache.selectedEvent = event
                val intent = Intent(context, EventDetailsActivity::class.java)
                context.startActivity(intent)
            }
        }
    }

    object EventComparator : DiffUtil.ItemCallback<Event>() {
        override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem == newItem
        }
    }
}
