package com.example.minisofascore.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.minisofascore.R

class DayAdapter(private val dayOfWeekNames: List<String>, private val dateOfMonthNames: List<String>)
    : RecyclerView.Adapter<DayAdapter.DayViewHolder>() {

    inner class DayViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val dayOfWeekTextView: TextView = view.findViewById(R.id.day_of_week)
        val dateOfMonthTextView: TextView = view.findViewById(R.id.date_of_month)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.day_item, parent, false)
        return DayViewHolder(view)
    }

    override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
        val dayOfWeek = dayOfWeekNames[position]
        val dateOfMonth = dateOfMonthNames[position]
        holder.dayOfWeekTextView.text = dayOfWeek
        holder.dateOfMonthTextView.text = dateOfMonth
    }

    override fun getItemCount() = dayOfWeekNames.size
}
