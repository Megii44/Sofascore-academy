package com.example.minisofascore.adapters.pager

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.minisofascore.R
import com.example.minisofascore.data.enums.SportEnum
import com.example.minisofascore.ui.events.EventsFragment
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
    private val fragmentFactories = mapOf(
        0 to { EventsFragment.newInstance(date, SportEnum.Football) },
        1 to { EventsFragment.newInstance(date, SportEnum.Basketball) },
        2 to { EventsFragment.newInstance(date, SportEnum.AmericanFootball) }
    )

    @RequiresApi(Build.VERSION_CODES.O)
    override fun createFragment(position: Int): Fragment {
        val fragmentFactory = fragmentFactories[position]
            ?: throw IllegalArgumentException("Invalid section index")
        return fragmentFactory()
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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getItemId(position: Int): Long {
        return date.toEpochDay() * 31 + position
    }

    fun getCurrentFragment(position: Int): Fragment? {
        val fragmentTag = "f$position"
        return (context as FragmentActivity).supportFragmentManager.findFragmentByTag(fragmentTag)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun containsItem(itemId: Long): Boolean {
        val position = (itemId % 31).toInt()
        val date = LocalDate.ofEpochDay(itemId / 31)
        return position in TAB_TITLES.indices && this.date == date
    }

    fun updateDateAndSport(date: LocalDate, sportPosition: Int) {
        this.date = date
        this.sportPosition = sportPosition

        // Notify the ViewPager that the data set has changed.
        // This will cause the ViewPager to refresh its views.
        notifyDataSetChanged()
    }
}
