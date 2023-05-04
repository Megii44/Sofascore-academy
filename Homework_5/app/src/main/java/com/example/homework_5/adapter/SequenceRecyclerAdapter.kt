package com.example.homework_5.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_5.R
import com.example.homework_5.databinding.TimeItemViewBinding
import com.example.homework_5.helpers.getFormattedTemperature
import com.example.homework_5.model.TimeItem

class SequenceRecyclerAdapter(
    private val context: Context,
    private var sequenceWeatherData: List<TimeItem>,
    ) : RecyclerView.Adapter<SequenceRecyclerAdapter.SequenceViewHolder>() {

    class SequenceViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = TimeItemViewBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SequenceViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.time_item_view, parent, false)
        return SequenceViewHolder(view)
    }

    override fun onBindViewHolder(holder: SequenceViewHolder, position: Int) {
        val data = sequenceWeatherData[position]

        holder.binding.apply {
            // Set the data you want to display for each recent search item
            // For example, set the location name in a TextView
            timeItemHour.text = data.hour.uppercase()
            timeItemWeatherIcon.setImageResource(data.icon)
            timeItemTemperature.text = getFormattedTemperature(data.temp_c.toInt())
        }
    }

    override fun getItemCount(): Int {
        return sequenceWeatherData.size
    }

    fun updateData(newSequenceWeatherData: List<TimeItem>) {
        sequenceWeatherData = newSequenceWeatherData
        notifyDataSetChanged()
    }
}
