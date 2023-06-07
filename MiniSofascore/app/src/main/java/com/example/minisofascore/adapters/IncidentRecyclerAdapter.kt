package com.example.minisofascore.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.minisofascore.R
import com.example.minisofascore.data.enums.IncidentTypeEnum
import com.example.minisofascore.data.models.Incident
import com.example.minisofascore.databinding.ItemIncidentBinding

class IncidentRecyclerAdapter : RecyclerView.Adapter<IncidentRecyclerAdapter.IncidentViewHolder>() {

    private val incidents: MutableList<Incident> = mutableListOf()

    class IncidentViewHolder(private val binding: ItemIncidentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(incident: Incident) {
            binding.incidentType.text = incident.type.toString()

            val (title, subtitle, logoResId) = when(incident.type) {
                IncidentTypeEnum.Card.toString() -> Triple(incident.player.name, "Foul", R.drawable.ic_card_yellow)
                IncidentTypeEnum.Goal.toString(), IncidentTypeEnum.Period.toString() -> Triple(incident.player.name, "${incident.time}'", R.drawable.ic_football)
                else -> throw IllegalArgumentException("Unexpected incident type: ${incident.type}")
            }

            binding.incidentTitle.text = title
            binding.incidentSubtitle.text = subtitle
            binding.incidentImageView.setImageResource(logoResId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IncidentViewHolder {
        val binding = ItemIncidentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IncidentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IncidentViewHolder, position: Int) {
        holder.bind(incidents[position])
    }

    override fun getItemCount(): Int = incidents.size

    fun submitList(newList: List<Incident>) {
        incidents.clear()
        incidents.addAll(newList)
        notifyDataSetChanged()
    }
}
