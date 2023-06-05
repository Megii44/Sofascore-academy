package com.example.minisofascore.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.minisofascore.R
import com.example.minisofascore.ui.american_football.AmericanFootballFragment
import com.example.minisofascore.ui.football.FootballFragment
import com.example.minisofascore.ui.basketball.BasketballFragment

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

class LeaguesSectionsPagerAdapter(private val context: Context, fm: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fm, lifecycle) {

    override fun getItemCount(): Int {
        return TAB_TITLES.size
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FootballFragment()
            1 -> BasketballFragment()
            2 -> AmericanFootballFragment()
            else -> throw IllegalArgumentException("Invalid tab position: $position")
        }
    }

    fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }
}
