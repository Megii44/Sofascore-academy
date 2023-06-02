package com.example.minisofascore

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.minisofascore.adapters.DayAdapter
import com.example.minisofascore.databinding.ActivityMainBinding
import com.example.minisofascore.ui.main.SectionsPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val days = mutableListOf<LocalDate>()

        // add past week
        for (i in 6 downTo 1) {
            days.add(LocalDate.now().minusDays(i.toLong()))
        }

        // add today
        days.add(LocalDate.now())

        // add next week
        for (i in 1..7) {
            days.add(LocalDate.now().plusDays(i.toLong()))
        }

        // format the days for display
        val dayOfWeekNames = days.map {
            if (it == LocalDate.now()) "TODAY"
            else it.dayOfWeek.toString().take(3).uppercase(Locale.ROOT)
        }

        val dateOfMonthNames = days.map { it.format(DateTimeFormatter.ofPattern("dd.MM")) }

        var sectionsPagerAdapter = SectionsPagerAdapter(this, days[0], 0)
        val viewPager: ViewPager2 = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs

        // Link TabLayout and ViewPager2
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = sectionsPagerAdapter.getPageTitle(position)
            tab.setIcon(sectionsPagerAdapter.getIcon(position))
        }.attach()

        val dayAdapter = DayAdapter(dayOfWeekNames, dateOfMonthNames) { position ->
            // Get the selected date and sport
            val selectedDate = days[position]
            val sportPosition = viewPager.currentItem

            // Update the SectionsPagerAdapter
            sectionsPagerAdapter.updateDateAndSport(selectedDate, sportPosition)
        }

        val daysList: RecyclerView = binding.daysList
        daysList.adapter = dayAdapter
    }
}
