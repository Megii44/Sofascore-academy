package com.example.minisofascore.ui.main

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
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

class SectionsPagerAdapter(private val context: Context, fm: FragmentManager, private val dates: List<LocalDate>) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> FootballFragment.newInstance(dates[position])
            1 -> BasketballFragment.newInstance(dates[position])
            2 -> AmericanFootballFragment.newInstance(dates[position])
            else -> throw IllegalArgumentException("Invalid section index")
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return context.resources.getString(TAB_TITLES[position])
    }

    fun getIcon(position: Int): Int {
        return TAB_ICONS[position]
    }

    override fun getCount(): Int {
        return TAB_TITLES.size
    }
}
