package com.example.minisofascore.adapters.recycler

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.minisofascore.databinding.DayItemBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class DaysRecyclerAdapter(
    private var days: MutableList<LocalDate>,
    private var dayOfWeekNames: MutableList<String>,
    private var dateOfMonthNames: MutableList<String>,
    private val onDayClicked: (position: Int) -> Unit
) : RecyclerView.Adapter<DaysRecyclerAdapter.DayViewHolder>() {
    private var recyclerView: RecyclerView? = null

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun loadMorePastDays() {
        val firstDate = days.first()
        val newDays = (1..7).map { firstDate.minusDays(it.toLong()) }
        days.addAll(0, newDays)
        dayOfWeekNames.addAll(0, newDays.map {
            if (it == LocalDate.now()) "TODAY"
            else it.dayOfWeek.toString().take(3).uppercase(Locale.ROOT)
        })
        dateOfMonthNames.addAll(0, newDays.map { it.format(DateTimeFormatter.ofPattern("dd.MM")) })
        notifyItemRangeInserted(0, newDays.size)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun loadMoreFutureDays() {
        val lastDate = days.last()
        val newDays = (1..7).map { lastDate.plusDays(it.toLong()) }
        days.addAll(newDays)
        dayOfWeekNames.addAll(newDays.map {
            if (it == LocalDate.now()) "TODAY"
            else it.dayOfWeek.toString().take(3).uppercase(Locale.ROOT)
        })
        dateOfMonthNames.addAll(newDays.map { it.format(DateTimeFormatter.ofPattern("dd.MM")) })
        notifyItemRangeInserted(days.size - newDays.size, newDays.size)
    }

    private fun addDays(newDays: List<LocalDate>) {
        val initialSize = days.size
        days.addAll(newDays)
        recyclerView?.post {
            notifyItemRangeInserted(initialSize, newDays.size)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
        val binding = DayItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return DayViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
        val date = days[position]
        val dayOfWeekName = if (date == LocalDate.now()) "TODAY" else date.dayOfWeek.toString().take(3).uppercase(Locale.ROOT)
        val dateOfMonthName = date.format(DateTimeFormatter.ofPattern("dd.MM"))

        holder.binding.dayOfWeek.text = dayOfWeekName
        holder.binding.dateOfMonth.text = dateOfMonthName

        // Load more days when we reach the end of the list
        if (position == days.size - 1) {
            val newDays = (1..14).map { days.last().plusDays(it.toLong()) }
            addDays(newDays)
        }
    }

    override fun getItemCount(): Int {
        return days.size
    }

    inner class DayViewHolder(val binding: DayItemBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onDayClicked(position)
                }
            }
        }
    }
}