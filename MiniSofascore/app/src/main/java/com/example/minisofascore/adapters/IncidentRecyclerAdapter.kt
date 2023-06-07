package com.example.minisofascore.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import com.example.minisofascore.R
import com.example.minisofascore.data.enums.IncidentTypeEnum
import com.example.minisofascore.data.models.Incident
import com.example.minisofascore.databinding.ItemIncidentBinding

class IncidentRecyclerAdapter(private val incidents: MutableList<Incident> = mutableListOf()) :
    RecyclerView.Adapter<IncidentRecyclerAdapter.IncidentViewHolder>() {

    class IncidentViewHolder(private val binding: ItemIncidentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(incident: Incident) {
            with(binding) {
                incidentType.text = incident.type.toString()
                var title: String? = null
                var subtitle: String? = null
                @DrawableRes var logoResId: Int? = null
                var side: String? = null

                when (incident.type) {
                    IncidentTypeEnum.Card.toString() -> {
                        title = incident.player?.name
                        subtitle = "Foul"
                        logoResId = R.drawable.ic_card_yellow
                        side = incident.teamSide
                    }
                    IncidentTypeEnum.Goal.toString() -> {
                        title = incident.player?.name
                        subtitle = "${incident.time}'"
                        logoResId = R.drawable.ic_football_green
                        side = incident.scoringTeam
                    }
                    IncidentTypeEnum.Period.toString() -> {
                        title = incident.player?.name
                        subtitle = "Argument"
                        logoResId = R.drawable.ic_football_green
                        side = ""
                    }
                    else -> throw IllegalArgumentException("Unexpected incident type: ${incident.type}")
                }

                if (side == "away") {
                    awayIncidentType.text = incident.type
                    awayIncidentTitle.text = title
                    awayIncidentSubtitle.text = subtitle
                    logoResId.let { awayIncidentImageView.setImageResource(it) }

                    incidentType.visibility = View.INVISIBLE
                    incidentTitle.visibility = View.INVISIBLE
                    incidentSubtitle.visibility = View.INVISIBLE
                    incidentImageView.visibility = View.INVISIBLE
                    line.visibility = View.INVISIBLE
                } else {
                    incidentType.text = incident.type
                    incidentTitle.text = title
                    incidentSubtitle.text = subtitle
                    logoResId.let { incidentImageView.setImageResource(it) }

                    awayIncidentType.visibility = View.INVISIBLE
                    awayIncidentTitle.visibility = View.INVISIBLE
                    awayIncidentSubtitle.visibility = View.INVISIBLE
                    awayIncidentImageView.visibility = View.INVISIBLE
                    awayLine.visibility = View.INVISIBLE
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IncidentViewHolder =
        IncidentViewHolder(
            ItemIncidentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: IncidentViewHolder, position: Int) =
        holder.bind(incidents[position])

    override fun getItemCount(): Int = incidents.size

    fun submitList(newList: List<Incident>) {
        incidents.run {
            clear()
            addAll(newList)
        }
        notifyDataSetChanged()
    }
}
