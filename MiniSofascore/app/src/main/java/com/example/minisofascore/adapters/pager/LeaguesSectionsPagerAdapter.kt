package com.example.minisofascore.adapters.pager

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.minisofascore.R
import com.example.minisofascore.ui.leagues.LeaguesFragment
import com.example.minisofascore.ui.utils.getSportForPosition

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

    fun getIcon(position: Int): Int {
        return TAB_ICONS[position]
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun createFragment(position: Int): Fragment {
        val sport = getSportForPosition(position)
        return LeaguesFragment.newInstance(sport)
    }

    fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }
}
