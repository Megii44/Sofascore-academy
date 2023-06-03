package com.example.minisofascore.ui.main

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.minisofascore.R
import com.example.minisofascore.adapters.DaysRecyclerAdapter
import com.example.minisofascore.adapters.SectionsPagerAdapter
import com.example.minisofascore.data.enums.SportEnum
import com.example.minisofascore.databinding.ActivityMainBinding
import com.example.minisofascore.ui.american_football.AmericanFootballFragment
import com.example.minisofascore.ui.basketball.BasketballFragment
import com.example.minisofascore.ui.football.FootballFragment
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

        viewModel.selectDay(LocalDate.now())
        viewModel.selectSport(SportEnum.Football.ordinal)

        viewModel.selectedSport.observe(this) { sport ->
            updateFragment(viewModel.selectedDay.value, sport)
        }

        viewModel.selectedDay.observe(this) { day ->
            updateFragment(day, viewModel.selectedSport.value)
        }

        val days = (6 downTo 1).map { LocalDate.now().minusDays(it.toLong()) } +
                listOf(LocalDate.now()) +
                (1..7).map { LocalDate.now().plusDays(it.toLong()) }

        val dayOfWeekNames = days.map {
            if (it == LocalDate.now()) "TODAY"
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
            viewModel.selectSport(position)
        }

        binding.daysList.adapter = daysRecyclerAdapter
    }

    private fun setupViewPagerWithTabs(sectionsPagerAdapter: SectionsPagerAdapter) {
        binding.viewPager.adapter = sectionsPagerAdapter

        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = sectionsPagerAdapter.getPageTitle(position)
            tab.setIcon(sectionsPagerAdapter.getIcon(position))
        }.attach()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun updateFragment(day: LocalDate?, sport: Int?) {
        if (day != null && sport != null) {
            binding.viewPager.setCurrentItem(sport, true)
        }
    }
}
