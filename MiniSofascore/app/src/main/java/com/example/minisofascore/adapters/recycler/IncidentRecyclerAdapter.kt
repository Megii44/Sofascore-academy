package com.example.minisofascore.adapters.recycler

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
                var title: String? = null
                var subtitle: String? = null
                @DrawableRes var logoResId: Int? = null
                var side: String? = null
                var type: String? = null
                var score: String? = null

                when (incident.type) {
                    IncidentTypeEnum.Card.toString() -> {
                        title = incident.player?.name
                        subtitle = "Foul"
                        logoResId = if(incident.color == "red") {
                            R.drawable.ic_card_red
                        } else {
                            R.drawable.ic_card_yellow
                        }
                        side = incident.teamSide
                        type = incident.time.toString() + "'"
                    }
                    IncidentTypeEnum.Goal.toString() -> {
                        title = incident.player?.name
                        subtitle = "${incident.time}'"
                        logoResId = R.drawable.ic_football_green
                        side = incident.scoringTeam
                        type = incident.time.toString() + "'"
                        score = incident.homeScore.toString() + " - " + incident.awayScore.toString()

                        awayIncidentSubtitle.visibility = View.INVISIBLE
                        incidentSubtitle.visibility = View.INVISIBLE
                    }
                    IncidentTypeEnum.Period.toString() -> {
                        title = incident.player?.name
                        subtitle = "Argument"
                        logoResId = R.drawable.ic_football_green
                        side = ""
                        type = incident.time.toString() + "'"
                    }
                    else -> throw IllegalArgumentException("Unexpected incident type: ${incident.type}")
                }

                // Reset visibility for all views
                incidentType.visibility = View.VISIBLE
                incidentTitle.visibility = View.VISIBLE
                incidentSubtitle.visibility = View.VISIBLE
                incidentImageView.visibility = View.VISIBLE
                line.visibility = View.VISIBLE
                awayIncidentType.visibility = View.VISIBLE
                awayIncidentTitle.visibility = View.VISIBLE
                awayIncidentSubtitle.visibility = View.VISIBLE
                awayIncidentImageView.visibility = View.VISIBLE
                awayLine.visibility = View.VISIBLE

                if (side == "away") {
                    awayIncidentType.text = type
                    awayIncidentTitle.text = title
                    awayIncidentSubtitle.text = subtitle
                    logoResId?.let { awayIncidentImageView.setImageResource(it) }
                    awayIncidentScore.text = score

                    incidentType.visibility = View.INVISIBLE
                    incidentTitle.visibility = View.INVISIBLE
                    incidentSubtitle.visibility = View.INVISIBLE
                    incidentImageView.visibility = View.INVISIBLE
                    incidentScore.visibility = View.INVISIBLE
                    line.visibility = View.INVISIBLE
                } else {
                    incidentType.text = type
                    incidentTitle.text = title
                    incidentSubtitle.text = subtitle
                    logoResId?.let { incidentImageView.setImageResource(it) }
                    incidentScore.text = score

                    awayIncidentType.visibility = View.INVISIBLE
                    awayIncidentTitle.visibility = View.INVISIBLE
                    awayIncidentSubtitle.visibility = View.INVISIBLE
                    awayIncidentImageView.visibility = View.INVISIBLE
                    awayIncidentScore.visibility = View.INVISIBLE
                    awayLine.visibility = View.INVISIBLE
                }

                if(incident.type == IncidentTypeEnum.Goal.toString()) {
                    awayIncidentSubtitle.visibility = View.INVISIBLE
                    incidentSubtitle.visibility = View.INVISIBLE
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
