package com.example.minisofascore.ui.main

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.minisofascore.adapters.DaysRecyclerAdapter
import com.example.minisofascore.adapters.SectionsPagerAdapter
import com.example.minisofascore.data.enums.SportEnum
import com.example.minisofascore.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        val today = LocalDate.now()
        viewModel.selectDay(today)
        viewModel.selectSport(SportEnum.Football.ordinal)

        val days = (6 downTo 1).map { today.minusDays(it.toLong()) } +
                listOf(today) +
                (1..7).map { today.plusDays(it.toLong()) }

        val dayOfWeekNames = days.map {
            if (it == today) "TODAY"
            else it.dayOfWeek.toString().take(3).uppercase(Locale.ROOT)
        }

        val dateOfMonthNames = days.map { it.format(DateTimeFormatter.ofPattern("dd.MM")) }

        val sectionsPagerAdapter = SectionsPagerAdapter(this, days[0], 0)
        setupViewPagerWithTabs(sectionsPagerAdapter)

        val daysRecyclerAdapter = DaysRecyclerAdapter(dayOfWeekNames, dateOfMonthNames) { position ->
            val selectedDate = days[position]
            val sportPosition = binding.viewPager.currentItem

            sectionsPagerAdapter.updateDateAndSport(selectedDate, sportPosition)
            viewModel.selectDay(selectedDate)
            // viewModel.selectSport(position) // Commented out as this could be incorrect. You might need to find another way to select the sport
        }

        binding.daysList.adapter = daysRecyclerAdapter

        viewModel.selectedSport.observe(this) { sport ->
            viewModel.selectedDay.value?.let { day ->
                updateFragment(day, sport)
            }
        }
    }

    private fun setupViewPagerWithTabs(sectionsPagerAdapter: SectionsPagerAdapter) {
        binding.viewPager.adapter = sectionsPagerAdapter

        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = sectionsPagerAdapter.getPageTitle(position)
            tab.setIcon(sectionsPagerAdapter.getIcon(position))
        }.attach()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun updateFragment(day: LocalDate, sport: Int) {
        binding.viewPager.setCurrentItem(sport, true)
    }
}
