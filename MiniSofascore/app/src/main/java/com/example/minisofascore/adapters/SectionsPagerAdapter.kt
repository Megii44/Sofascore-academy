package com.example.minisofascore.adapters

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.minisofascore.R
import com.example.minisofascore.ui.american_football.AmericanFootballFragment
import com.example.minisofascore.ui.basketball.BasketballFragment
import com.example.minisofascore.ui.football.FootballFragment
import java.time.LocalDate

private val TAB_TITLES = arrayOf(
    R.string.football_title,
    R.string.basketball_title,
    R.string.am_football_title
)

private val TAB_ICONS = arrayOf(
    R.drawable.ic_football,
    R.drawable.ic_basketball,
    R.drawable.ic_american_football
)

class SectionsPagerAdapter(
    private val context: Context,
    private var date: LocalDate,
    private var sportPosition: Int
) : FragmentStateAdapter(context as FragmentActivity) {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FootballFragment.newInstance(date)
            1 -> BasketballFragment.newInstance(date)
            2 -> AmericanFootballFragment.newInstance(date)
            else -> throw IllegalArgumentException("Invalid section index")
        }
    }

    fun getPageTitle(position: Int): CharSequence {
        return context.resources.getString(TAB_TITLES[position])
    }

    fun getIcon(position: Int): Int {
        return TAB_ICONS[position]
    }

    override fun getItemCount(): Int {
        return TAB_TITLES.size
    }

    fun updateDateAndSport(date: LocalDate, sportPosition: Int) {
        this.date = date
        this.sportPosition = sportPosition

        // Notify the ViewPager that the data set has changed.
        // This will cause the ViewPager to refresh its views.
        notifyDataSetChanged()
    }
}
