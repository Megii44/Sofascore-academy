package com.example.minisofascore.ui.main

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.minisofascore.adapters.recycler.DaysRecyclerAdapter
import com.example.minisofascore.adapters.pager.SectionsPagerAdapter
import com.example.minisofascore.data.enums.SportEnum
import com.example.minisofascore.databinding.ActivityMainBinding
import com.example.minisofascore.ui.leagues.LeaguesActivity
import com.example.minisofascore.ui.settings.SettingsActivity
import com.google.android.material.tabs.TabLayoutMediator
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.minisofascore.R
import com.example.minisofascore.ui.events.EventsFragment
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

        val days = (-6..7).map { today.plusDays(it.toLong()) }

        val dayOfWeekNames = days.map {
            if (it == today) getString(R.string.today).uppercase()
            else it.dayOfWeek.toString().take(3).uppercase(Locale.ROOT)
        }

        val dateOfMonthNames = days.map { it.format(DateTimeFormatter.ofPattern("dd.MM")) }

        val sectionsPagerAdapter = SectionsPagerAdapter(this, days[0], SportEnum.Football.ordinal)
        setupViewPagerWithTabs(sectionsPagerAdapter)

        val daysRecyclerAdapter = DaysRecyclerAdapter(
            days.toMutableList(), dayOfWeekNames.toMutableList(), dateOfMonthNames.toMutableList()
        ) { position ->
            val selectedDate = days[position]
            viewModel.selectDay(selectedDate)
        }

        with(binding.daysList) {
            adapter = daysRecyclerAdapter
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val layoutManager = layoutManager as LinearLayoutManager
                    val totalItemCount = layoutManager.itemCount
                    val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                    val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()

                    if (totalItemCount <= (lastVisibleItemPosition + 2)) {
                        daysRecyclerAdapter.loadMoreFutureDays()
                    }
                    if (firstVisibleItemPosition <= 2) {
                        daysRecyclerAdapter.loadMorePastDays()
                    }
                }
            })
        }

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                val sportEnum = SportEnum.values()[position]
                viewModel.selectSport(sportEnum.ordinal)

                val eventFragment = sectionsPagerAdapter.getCurrentFragment(position) as? EventsFragment

                viewModel.selectedDay.value?.let { eventFragment?.viewModel?.updateDateAndSport(it, sportEnum) }

                super.onPageSelected(position)
            }
        })

        viewModel.selectedDay.observe(this) { day ->
            viewModel.selectedSport.value?.let { sport ->
                sectionsPagerAdapter.updateDateAndSport(day, sport)
            }
        }

        binding.iconTrophy.setOnClickListener {
            val intent = Intent(this, LeaguesActivity::class.java)
            startActivity(intent)
        }

        binding.iconSettings.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupViewPagerWithTabs(sectionsPagerAdapter: SectionsPagerAdapter) {
        binding.viewPager.adapter = sectionsPagerAdapter

        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = sectionsPagerAdapter.getPageTitle(position)
            tab.setIcon(sectionsPagerAdapter.getIcon(position))
        }.attach()
    }
}
