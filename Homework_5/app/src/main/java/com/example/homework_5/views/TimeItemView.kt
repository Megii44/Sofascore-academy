package com.example.homework_5.views

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.drawable.toDrawable
import com.example.homework_5.R
import com.example.homework_5.databinding.TimeItemViewBinding

class TimeItemView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private val binding: TimeItemViewBinding

    private val hour: String
    private val icon: Drawable
    private val temperature: Int
    private val backgroundColor: Int

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.time_item_view, this, true)
        binding = TimeItemViewBinding.bind(view)
        context.obtainStyledAttributes(
            attrs,
            R.styleable.TimeItemView, 0, 0
        ).apply {
            try {
                hour = getString(R.styleable.TimeItemView_time_item_hour)!!
                icon = getDrawable(R.styleable.TimeItemView_time_item_icon)!!
                temperature = getInt(R.styleable.TimeItemView_time_item_temperature, 0)
                backgroundColor = getColor(R.styleable.TimeItemView_time_item_background_color, 0)

                setHour(hour)
                setIcon(icon)
                setTemperature(temperature)
                setItemBackgroundColor(backgroundColor)
            } finally {
                recycle()
            }
        }
    }

    private fun setHour(hour: String) {
        binding.timeItemHour.text = hour
    }

    private fun setIcon(icon: Drawable) {
        binding.timeItemWeatherIcon.setImageDrawable(icon)
    }

    private fun setTemperature(temperature: Int) {
        binding.timeItemTemperature.text = temperature.toString()
    }

    private fun setItemBackgroundColor(backgroundColor: Int) {
        binding.timeItem.background = backgroundColor.toDrawable()
    }
}
