package com.example.minisofascore.ui.team

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.minisofascore.R
import com.example.minisofascore.databinding.ActivityTeamDetailsBinding
import com.example.minisofascore.ui.team.details.TeamDetailsFragment
import com.example.minisofascore.ui.team.matches.TeamMatchesFragment
import com.example.minisofascore.ui.team.squad.TeamSquadFragment
import com.example.minisofascore.ui.team.standings.TeamStandingsFragment
import com.google.android.material.tabs.TabLayoutMediator

class TeamDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTeamDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeamDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inflate custom toolbar layout and add it to the toolbar
        val actionBarLayout = layoutInflater.inflate(R.layout.custom_action_bar, null)
        binding.teamDetailsToolbar.addView(actionBarLayout)

        // Now set the toolbar as support action bar
        setSupportActionBar(binding.teamDetailsToolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        // Set toolbar title
        val title = "njnj"
        val actionBarTitle = actionBarLayout.findViewById<View>(R.id.action_bar_title)
        if (actionBarTitle is TextView) {
            actionBarTitle.text = title
        }

        // Set toolbar image
        val actionBarImage = actionBarLayout.findViewById<View>(R.id.action_bar_image)
        //if (actionBarImage is ImageView) {
        //    Glide.with(this)
        //      .load(tournamentLogoUrl)
        //    .into(actionBarImage)
        //}

        val teamId = intent.getStringExtra("team_id") ?: ""

        val viewPager = binding.viewPager
        viewPager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int = 4

            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    0 -> TeamDetailsFragment.newInstance(teamId)
                    1 -> TeamMatchesFragment.newInstance(teamId)
                    2 -> TeamStandingsFragment.newInstance(teamId)
                    3 -> TeamSquadFragment.newInstance(teamId)
                    else -> Fragment() // return an empty fragment
                }
            }
        }

        val tabLayout = binding.tabLayout
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Team Details"
                1 -> "Matches"
                2 -> "Standings"
                3 -> "Squad"
                else -> null
            }
        }.attach()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
