package com.example.minisofascore

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.minisofascore.adapters.DayAdapter
import com.example.minisofascore.databinding.ActivityMainBinding
import com.example.minisofascore.ui.main.SectionsPagerAdapter
import com.google.android.material.tabs.TabLayout
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

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

        // Set tab icons
        for (i in 0 until tabs.tabCount) {
            tabs.getTabAt(i)?.setIcon(sectionsPagerAdapter.getIcon(i))
        }

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

        val dayAdapter = DayAdapter(dayOfWeekNames, dateOfMonthNames)
        val daysList: RecyclerView = findViewById(R.id.days_list)
        daysList.adapter = dayAdapter

    }
}