package com.example.minisofascore.ui.team

import android.graphics.PorterDuff
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.minisofascore.R
import com.example.minisofascore.data.factories.TeamViewModelFactory
import com.example.minisofascore.data.models.Team
import com.example.minisofascore.data.repositories.CountryRepository
import com.example.minisofascore.data.repositories.TeamRepository
import com.example.minisofascore.databinding.ActivityTeamDetailsBinding
import com.example.minisofascore.ui.team.details.TeamDetailsFragment
import com.example.minisofascore.ui.team.matches.TeamMatchesFragment
import com.example.minisofascore.ui.team.squad.TeamSquadFragment
import com.example.minisofascore.ui.team.standings.TeamStandingsFragment
import com.example.minisofascore.ui.utils.loadImage
import com.example.minisofascore.ui.utils.loadRoundImage
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.launch

class TeamDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTeamDetailsBinding
    private lateinit var viewModel: TeamViewModel
    private lateinit var teamRepository: TeamRepository
    private lateinit var countryRepository: CountryRepository
    private lateinit var viewModelFactory: TeamViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeamDetailsBinding.inflate(layoutInflater)

        // Create the repository instance
        teamRepository = TeamRepository()
        countryRepository = CountryRepository()

        // Initialize the ViewModel using the custom factory
        viewModelFactory = TeamViewModelFactory(teamRepository, countryRepository)
        viewModel = ViewModelProvider(this, viewModelFactory)[TeamViewModel::class.java]

        setContentView(binding.root)

        // Get team id
        val team: Team? = TeamCache.selectedTeam
        team?.let { it ->
            lifecycleScope.launch {
                viewModel.apply {
                    fetchCountryFlag(it.country.name)
                }
            }
        }

        // Inflate custom toolbar layout and add it to the toolbar
        val actionBarLayout = layoutInflater.inflate(R.layout.toolbar_layout_team_details, null)
        binding.teamDetailsToolbar.addView(actionBarLayout)

        // Now set the toolbar as support action bar
        setSupportActionBar(binding.teamDetailsToolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        // Set back arrow icon color to white
        val upArrow = ContextCompat.getDrawable(this, R.drawable.ic_back_arrow)
        val colorWhite = ContextCompat.getColor(this, R.color.white)
        upArrow?.let {
            DrawableCompat.setTint(it, colorWhite)
            DrawableCompat.setTintMode(it, PorterDuff.Mode.SRC_ATOP)
            supportActionBar?.setHomeAsUpIndicator(it)
        }

        // Set toolbar title
        val title = team?.name
        val actionBarTitle = actionBarLayout.findViewById<View>(R.id.team_name_text_view)
        if (actionBarTitle is TextView) {
            actionBarTitle.text = title
        }

        val country = team?.country?.name
        val actionBarCountry = actionBarLayout.findViewById<View>(R.id.country_text_view)
        if (actionBarCountry is TextView) {
            actionBarCountry.text = country
        }

        // Set toolbar image
        val teamLogoUrl = "https://academy.dev.sofascore.com/team/${team?.id}/image"

        val actionBarImage = actionBarLayout.findViewById<ImageView>(R.id.team_logo_image_view)
        loadImage(this, teamLogoUrl, actionBarImage)

        // Set country image
        val countryImageView = actionBarLayout.findViewById<ImageView>(R.id.country_logo_image_view)

        viewModel.countryFlag.observe(this) { c ->
            val flagUrl = c.flags.png
            loadRoundImage(this, flagUrl, countryImageView)
        }

        val viewPager = binding.viewPager
        viewPager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int = 4

            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    0 -> TeamDetailsFragment.newInstance(team?.id.toString())
                    1 -> TeamMatchesFragment.newInstance(team?.id.toString())
                    2 -> TeamStandingsFragment.newInstance(team?.id.toString())
                    3 -> TeamSquadFragment.newInstance(team?.id.toString())
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
