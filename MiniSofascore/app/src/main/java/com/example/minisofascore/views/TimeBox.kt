package com.example.minisofascore.views
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.minisofascore.R
import com.example.minisofascore.databinding.SampleTimeBoxBinding

class TimeBox(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private val binding: SampleTimeBoxBinding

    private var team1: String? = null
    private var team2: String? = null
    private var logo1: Drawable? = null
    private var logo2: Drawable? = null
    private var score1: Int = 0
    private var score2: Int = 0
    private var startTime: Double = 0.0
    private var overTime: Double = 0.0

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.sample_time_box, this, true)
        binding = SampleTimeBoxBinding.bind(view)
        context.obtainStyledAttributes(
            attrs,
            R.styleable.TimeBox, 0, 0
        ).apply {
            try {
                team1 = getString(R.styleable.TimeBox_titleTeam1)
                team2 = getString(R.styleable.TimeBox_titleTeam1)
                logo1 = getDrawable(R.styleable.TimeBox_logoTeam1)
                logo2 = getDrawable(R.styleable.TimeBox_logoTeam2)
                score1 = getInt(R.styleable.TimeBox_scoreTeam1, 0)
                score2 = getInt(R.styleable.TimeBox_scoreTeam2, 0)
                // assuming that startTime and overTime are float attributes in your styleable
                startTime = getFloat(R.styleable.TimeBox_startTime, 0.0f).toDouble()
                overTime = getFloat(R.styleable.TimeBox_overTime, 0.0f).toDouble()

                setTeam(team1, 0)
                setTeam(team2, 1)
                setTeamLogo(logo1, 0)
                setTeamLogo(logo2, 1)
                setTeamScore(score1, 0)
                setTeamScore(score2, 1)
                setStartTime(startTime)
                setOverTime(overTime)
            } finally {
                recycle()
            }
        }
    }

    private fun setTeam(team: String?, index: Int) {
        if (index == 1) {
            binding.titleTeam2.text = team
        } else {
            binding.titleTeam1.text = team
        }
    }

    private fun setTeamScore(score: Int, index: Int) {
        if (index == 1) {
            binding.scoreTeam2.text = score.toString()
        } else {
            binding.scoreTeam1.text = score.toString()
        }
    }

    private fun setTeamLogo(logo: Drawable?, index: Int) {
        if (index == 1) {
            binding.logoTeam2.setImageDrawable(logo)
        } else {
            binding.logoTeam1.setImageDrawable(logo)
        }
    }

    private fun setStartTime(startTime: Double) {
        binding.startTime.text = startTime.toString()
    }

    private fun setOverTime(overTime: Double) {
        binding.overTime.text = overTime.toString()
    }
}
