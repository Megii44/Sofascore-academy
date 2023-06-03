package com.example.minisofascore.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.minisofascore.databinding.DayItemBinding

class DaysRecyclerAdapter(
    private val dayOfWeekNames: List<String>,
    private val dateOfMonthNames: List<String>,
    private val onDayClicked: (position: Int) -> Unit
) : RecyclerView.Adapter<DaysRecyclerAdapter.DayViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
        val binding = DayItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return DayViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
        val dayOfWeekName = dayOfWeekNames[position]
        val dateOfMonthName = dateOfMonthNames[position]

        holder.binding.dayOfWeek.text = dayOfWeekName
        holder.binding.dateOfMonth.text = dateOfMonthName
    }

    override fun getItemCount(): Int {
        // assuming both lists have the same size
        return dayOfWeekNames.size
    }

    inner class DayViewHolder(val binding: DayItemBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onDayClicked(position)

                    // Access TextView text
                    val date = dateOfMonthNames[position]

                    // Update selected day in main activity view model

                }
            }
        }
    }

}
